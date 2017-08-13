package ua.kiev.prog.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ua.kiev.prog.exception.PhotoErrorException;
import ua.kiev.prog.exception.PhotoNotFoundException;

@Controller
public class MyController {

	private Map<Long, byte[]> photos = new HashMap<Long, byte[]>();
	private String[] selectedId;

	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	@PostMapping("/addPhoto")
	public String addPhoto(@RequestParam("photo") MultipartFile photo, Model model) {
		if (photo.isEmpty())
			throw new PhotoErrorException();

		try {
			long id = System.currentTimeMillis();
			photos.put(id, photo.getBytes());

			model.addAttribute("photoId", id);
			System.out.println("Photo added: " + id);
			System.out.println("Now in the container:");
			for (Long photoId : photos.keySet()) {
				System.out.println("Photo id : " + photoId);
			}
			return "result";
		} catch (IOException e) {
			throw new PhotoErrorException();
		}
	}

	@GetMapping("/showAll")
	public String showAll(Model model) {
		model.addAttribute("photos", photos);
		return "container";
	}

	@PostMapping("/processSelected")
	public String processSelected(HttpServletRequest request) throws IOException {
		selectedId = request.getParameterValues("id");
		String action = request.getParameter("action");
		if (action.equals("delete")) {
			for (String stringId : selectedId) {
				photos.remove(Long.valueOf(stringId));
				System.out.println("Image removed: " + stringId);
			}
			selectedId = null;
			return "redirect:/";
		} else {
			return "redirect:/zip";

		}
	}

	@GetMapping(name = "/zip", produces = "application/zip")
	public ResponseEntity<byte[]> createZip() throws IOException {
		byte[] output = null;
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
				ZipOutputStream zos = new ZipOutputStream(bos)) {
			zos.setMethod(ZipOutputStream.DEFLATED);
			zos.setLevel(3);
			for (String id : selectedId) {
				ZipEntry zipEntry = new ZipEntry(id + ".jpeg");
				byte[] tempArray = photos.get(Long.valueOf(id));
				zipEntry.setSize(tempArray.length);
				zos.putNextEntry(zipEntry);
				zos.write(tempArray);
				zos.closeEntry();
			}
			zos.close();
			output = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		selectedId = null;
		return ResponseEntity.ok().contentLength(output.length).body(output);

	}

	@GetMapping("/show/{photoId}")
	public ResponseEntity<byte[]> onPhoto(@PathVariable("photoId") long id) {
		return photoById(id);
	}

	@PostMapping("/view")
	public ResponseEntity<byte[]> onView(@RequestParam("photoId") long id) {
		return photoById(id);
	}

	@GetMapping("/delete/{photoId}")
	public String onDelete(@PathVariable("photoId") long id) {
		if (photos.remove(id) == null)
			throw new PhotoNotFoundException();
		else
			System.out.println("Photo deleted: " + id);
		System.out.println("Now in the container:");
		for (Long photoId : photos.keySet()) {
			System.out.println("Photo id : " + photoId);
		}
		return "redirect:/";
	}

	private ResponseEntity<byte[]> photoById(long id) {
		byte[] bytes = photos.get(id);
		if (bytes == null)
			throw new PhotoNotFoundException();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}
}
