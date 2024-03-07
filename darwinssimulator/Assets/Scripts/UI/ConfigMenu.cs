using UnityEngine.SceneManagement;
using UnityEngine;
using System.Linq;
using UnityEngine.UI;
using UnityEngine.UIElements;

public class ConfigMenu : MonoBehaviour
{
    public GameObject configMenu;
    public GameObject gameInterface;
    public GameObject npcManagerObject;
    public GameObject levelControllerObject;
    public GameObject npcSliderObject;
    public GameObject game;
    public GameObject mainMenu;
    public Terrain terrain;
    private void Start()
    {
    }

    public void StartGame()
    {
        configMenu.SetActive(false);
        gameInterface.SetActive(true);
        levelControllerObject.GetComponent<LevelController>().SizeSetup(); // D�finit la taille de la map
        npcManagerObject.GetComponent<NPCManager>().InitialSpawnNPC(); // D�finit le nb de NPC qui spawn
        levelControllerObject.GetComponent<LevelController>().SpawnObstacles(); // spawn obstacles
        levelControllerObject.GetComponent<LevelController>().RespawnVegetation(); // respawn vegetation
        Time.timeScale = 1f;
        game.GetComponent<GameScript>().gamePlaying = true;
    }

    public void FastStart()
    {
        levelControllerObject.GetComponent<LevelController>().FoodNumber = 65;
        levelControllerObject.GetComponent<LevelController>().FloorSize = 40;
        levelControllerObject.GetComponent<LevelController>().StartingPopulation = 15;
        levelControllerObject.GetComponent<LevelController>().repopingLimit = 10;

        mainMenu.SetActive(false);
        gameInterface.SetActive(true);
        levelControllerObject.GetComponent<LevelController>().SizeSetup(); // D�finit la taille de la map
        npcManagerObject.GetComponent<NPCManager>().InitialSpawnNPC(); // D�finit le nb de NPC qui spawn
        levelControllerObject.GetComponent<LevelController>().SpawnObstacles(); // spawn obstacles
        levelControllerObject.GetComponent<LevelController>().RespawnVegetation(); // respawn vegetation
        Time.timeScale = 1f;
        game.GetComponent<GameScript>().gamePlaying = true;
    }



    private void Update()
    {
    }
}
