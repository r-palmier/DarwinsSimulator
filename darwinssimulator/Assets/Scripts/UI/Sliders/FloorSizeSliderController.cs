using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class FloorSizeSliderController : MonoBehaviour
{
    [SerializeField] private Slider floorSizeSlider;
    [SerializeField] private Text sliderText;
    public float sliderValue;
    public GameObject levelControllerObject;

    // Start is called before the first frame update
    void Start()
    {
        floorSizeSlider.onValueChanged.AddListener((v) =>
        {
            float roundedValue = Mathf.Round(v / 5) * 5;
            floorSizeSlider.value = roundedValue;
            sliderText.text = roundedValue.ToString("0");
            sliderValue = roundedValue;
            // Envois la starting population au levelController
            levelControllerObject.GetComponent<LevelController>().FloorSize = (int)roundedValue;
        });
    }

    // Update is called once per frame
    void Update()
    {

    }
}
