using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class FoodSliderController : MonoBehaviour
{
    [SerializeField] private Slider foodSlider;
    [SerializeField] private Text sliderText;
    public float sliderValue;
    public GameObject levelControllerObject;

    // Start is called before the first frame update
    void Start()
    {
        foodSlider.onValueChanged.AddListener((v) =>
        {
            float roundedValue = Mathf.Round(v / 5) * 5;
            foodSlider.value = roundedValue;
            sliderText.text = roundedValue.ToString("0");
            sliderValue = roundedValue;
            // Envois la starting population au levelController
            levelControllerObject.GetComponent<LevelController>().FoodNumber = (int)roundedValue;
        });
    }

    // Update is called once per frame
    void Update()
    {

    }
}
