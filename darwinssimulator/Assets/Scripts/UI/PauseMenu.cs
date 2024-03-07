using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PauseMenu : MonoBehaviour
{
    public GameObject pauseMenu;
    public GameObject inGameInterface;
    public GameObject game;
    public float game_speed; // Sert à sauvegarder la vitesse à laquelle allait le jeu avant que le joueur mette pause

    // Start is called before the first frame update
    void Start()
    {
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            pauseMenu.GetComponent<PauseMenu>().ResumeGame();
        }
    }

    public void PauseGame()
    {
        inGameInterface.SetActive(false);
        pauseMenu.SetActive(true);
        game.GetComponent<GameScript>().gamePaused = true;
        game_speed = Time.timeScale;
        Time.timeScale = 0f;

        // Set gamePlaying to true
        game.GetComponent<GameScript>().gamePlaying = false;
    }
    public void ResumeGame()
    {
        pauseMenu.SetActive(false);
        inGameInterface.SetActive(true);
        game.GetComponent<GameScript>().gamePaused = false;
        Time.timeScale = game_speed; // Remet le jeu à sa vitesse initiale avant la pause

        // Set gamePlaying to false
        game.GetComponent<GameScript>().gamePlaying = true;
    }
}
