using System.Collections;
using System.Collections.Generic;
using System.Xml.Linq;
using UnityEngine;

public class UI : MonoBehaviour
{
    public GameObject game;
    public GameObject mainMenu;
    // Start is called before the first frame update
    void Start()
    {
        Time.timeScale = 0f;
        game.GetComponent<GameScript>().gamePaused = false;
        mainMenu.SetActive(true);
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
