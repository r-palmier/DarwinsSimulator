using UnityEngine.SceneManagement;
using UnityEngine;
using System.Linq;
using UnityEngine.UI;

public class MainMenu : MonoBehaviour
{
    public GameObject mainMenu;
    public GameObject configMenu;
    public GameObject pauseMenu;
    public GameObject gameInterface;
    public GameObject settingsMenu;

    public void GoToMenu()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
    }

    public void GoToConfig()
    {
        mainMenu.SetActive(false);
        configMenu.SetActive(true);
    }

    public void BackToMainMenu()
    {
        mainMenu.SetActive(true);
        settingsMenu.SetActive(false);
    }

}
