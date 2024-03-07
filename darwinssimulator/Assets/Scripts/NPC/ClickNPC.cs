using UnityEngine;

public class ClickNPC : MonoBehaviour
{

    public GameObject healthBarCanvas;
    public GameObject energybarCanvas;

    private void Start()
    {
        energybarCanvas.SetActive(false);
    }

    private void OnMouseDown()
    {
        // Ajoutez ici le code � ex�cuter lorsque le NPC est cliqu�
    }

    private void OnMouseEnter()
    {
        energybarCanvas.SetActive(true); // Active le canvas de la barre de sant�

        // Ajoutez ici le code � ex�cuter lorsque la souris entre sur le NPC
    }

    private void OnMouseExit()
    {
        energybarCanvas.SetActive(false); // Active le canvas de la barre de sant�

        // Ajoutez ici le code � ex�cuter lorsque la souris quitte le NPC
    }
}
