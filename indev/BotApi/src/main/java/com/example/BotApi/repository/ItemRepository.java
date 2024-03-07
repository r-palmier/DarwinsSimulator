package com.example.BotApi.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BotApi.model.DiscordUser;
import com.example.BotApi.model.Item;
import com.example.BotApi.model.Tag;

/**
 * 
 * 
 * @author Gervais Pierre
 *
 */
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
	public Page<Item> findAllByTagsIn(Set<Tag> tags, Pageable pageable);
	
	public Page<Item> findAllByNameStartingWithIgnoreCase(String name, Pageable pageable);
	
	public Page<Item> findAllBydiscordUser(DiscordUser discordUser, Pageable pageable);
}
