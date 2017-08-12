package ua.kiev.prog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

	@PostMapping("/deleteSelected")
	public String deleteSelected(HttpServletRequest request) {
		String[] selected = request.getParameterValues("id");
		for (String stringId : selected) {
			photos.remove(Long.valueOf(stringId));
			System.out.println("Image removed: " + stringId);
		}
		return "redirect:/";

	}

	@GetMapping("/show/{photo_id}")
	public ResponseEntity<byte[]> onPhoto(@PathVariable("photo_id") long id) {
		return photoById(id);
	}

	@PostMapping("/view")
	public ResponseEntity<byte[]> onView(@RequestParam("photo_id") long id) {
		return photoById(id);
	}

	@GetMapping("/delete/{photo_id}")
	public String onDelete(@PathVariable("photo_id") long id) {
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
