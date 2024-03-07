using UnityEngine;

public class DayNightCycle : MonoBehaviour
{
    [SerializeField] private Material originalSkybox;
    [SerializeField] private Light directionalLight; // Directional light à synchroniser avec le cycle jour/nuit
    private Material skybox;
    private float _timeScale = 1f / 120f; // 120 secondes pour un cycle complet (60 secondes pour le jour et 60 secondes pour la nuit)
    private static readonly int Rotation = Shader.PropertyToID("_Rotation");
    private static readonly int Exposure = Shader.PropertyToID("_Exposure");

    void Start()
    {
        // Clone the original Skybox material at the start of the script
        skybox = new Material(originalSkybox);
        RenderSettings.skybox = skybox;
    }

    // Update is called once per frame
    void Update()
    {
        float elapsedTime = Time.time * _timeScale; // Le temps écoulé, modifié par _timeScale

        skybox.SetFloat(Rotation, elapsedTime * 360f); // Rotation de la Skybox

        // Calcule l'exposition en fonction du temps. Le sinus oscille entre -1 et 1, nous le convertissons en une gamme de 0.15 à 1
        float exposure = Mathf.Lerp(0.15f, 1f, (Mathf.Sin(elapsedTime * 2 * Mathf.PI) + 1) / 2f);
        skybox.SetFloat(Exposure, exposure);

        // Si une Directional Light a été assignée, on change sa rotation pour qu'elle corresponde au cycle jour/nuit
        if (directionalLight != null)
        {
            // On définit l'angle de la lumière directionnelle pour correspondre à l'heure du jour (0° = minuit, 180° = midi, 360° = minuit suivant)
            float angle = elapsedTime * 360f;
            // On convertit cet angle en une direction pour la lumière
            Vector3 lightDirection = new Vector3(0, -Mathf.Cos(angle * Mathf.Deg2Rad), -Mathf.Sin(angle * Mathf.Deg2Rad));
            // On applique cette direction à la lumière
            directionalLight.transform.forward = lightDirection;
        }
    }
}
