package com.example.BotApi.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BotApi.model.DiscordUser;

/**
 * 
 * 
 * @author Gervais Pierre
 *
 */
public interface DiscordUserRepository extends JpaRepository<DiscordUser, Integer> {

	public DiscordUser findByuserId(String userId);
	
	public ArrayList<DiscordUser> findAllByuserId(String userId);
	
}
