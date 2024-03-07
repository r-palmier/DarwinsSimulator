using QuantumTek.QuantumUI;

using UnityEngine;

public class InGame : MonoBehaviour
{
    public GameObject pauseMenu;
    public GameObject game;
    public QUI_OptionList optionList;
    public GameObject levelControllerObject;

    // Start is called before the first frame update
    void Start()
    {
        optionList.onChangeOption.AddListener(GameSpeed);
    }

    // Tant que la game interface est active, on prends en compte la touche ESPACE pour mettre le jeu en pause
    // car si game interface n'est pas active, l'update ne fonctionnera pas
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            pauseMenu.GetComponent<PauseMenu>().PauseGame();
        }
        if (Input.GetKeyDown(KeyCode.L))
        {
            levelControllerObject.GetComponent<LevelController>().RespawnVegetation(); // D�finit la taille de la map
        }

    }

    public void GameSpeed()
    {
        if (optionList.optionIndex == 0)
        {
            Time.timeScale = 1f;
        }
        else if (optionList.optionIndex == 1)
        {
            Time.timeScale = 2f;
        }
        else if (optionList.optionIndex == 2)
        {
            Time.timeScale = 4f;
        }
        else if (optionList.optionIndex == 3)
        {
            Time.timeScale = 8f;
        }
        else if (optionList.optionIndex == 4)
        {
            Time.timeScale = 16f;
        }
        else if (optionList.optionIndex == 5)
        {
            Time.timeScale = 32f;
        }
        else if (optionList.optionIndex == 6)
        {
            Time.timeScale = 64f;
        }
        else if (optionList.optionIndex == 7)
        {
            Time.timeScale = 0.25f;
        }
        else if (optionList.optionIndex == 8)
        {
            Time.timeScale = 0.5f;
        }

    }
}
