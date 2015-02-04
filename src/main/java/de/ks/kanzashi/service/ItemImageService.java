package de.ks.kanzashi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.ks.kanzashi.entity.ItemImage;
import de.ks.kanzashi.repository.ItemImageRepository;

@Service
public class ItemImageService {
	
	@Autowired
	private ItemImageRepository itemImageRepository;

	public void save(ItemImage itemImage) {
		itemImageRepository.save(itemImage);
	}

	public byte[] loadImage(int id) {
		return itemImageRepository.findById(id).getImage();
	}

}
