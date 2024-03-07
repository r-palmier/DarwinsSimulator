using UnityEngine.SceneManagement;
using UnityEngine;
using System.Linq;
using UnityEngine.UI;
using UnityEngine.UIElements;

public class SettingsMenu : MonoBehaviour
{
    public GameObject mainMenu;
    public GameObject settingsMenu;

    public void GoToConfig()
    {
        mainMenu.SetActive(false);
        settingsMenu.SetActive(true);
    }
}
