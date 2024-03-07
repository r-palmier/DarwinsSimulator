using sc.terrain.vegetationspawner;
using UnityEngine;
using UnityEngine.UI;
using static sc.terrain.vegetationspawner.VegetationSpawner;

public class LevelController : MonoBehaviour
{
    public GameObject game;

    public GameObject food;
    public GameObject[] listFood;
    public float timer = 0;
    [SerializeField] private int foodNumber;

    public GameObject floor;

    public GameObject wallUp;
    public GameObject wallDown;
    public GameObject wallRight;
    public GameObject wallLeft;

    //Obstacles
    public GameObject Obstacle;
    private GameObject[] ListObstacle;

    [SerializeField] private float floorSize;

    [SerializeField] public int startingPopulation;
    [SerializeField] public int repopingLimit;
    public VegetationSpawner vegetationSpawner;

    // Start is called before the first frame update
    void Start()
    {
    }

    private void Update()
    {
        if (game.GetComponent<GameScript>().gamePlaying == true)
        {
            SpawnFood();
        }
    }

    public void RespawnVegetation()
    {
        vegetationSpawner.Respawn();
    }

    public void SizeSetup()
    {
        // Assurez-vous que "floor" est de type Terrain et non GameObject.
        Terrain terrain = floor.GetComponent<Terrain>();
        TerrainData terrainData = terrain.terrainData;

        terrainData.size = new Vector3(FloorSize, 0.1f, FloorSize);

        wallUp.transform.localScale = new Vector3(FloorSize, 3, 1);
        wallUp.transform.position = new Vector3(FloorSize / 2, 1, FloorSize); //modifié

        wallDown.transform.localScale = new Vector3(FloorSize, 3, 1);
        wallDown.transform.position = new Vector3(FloorSize / 2, 1, 0); //modifié

        wallLeft.transform.localScale = new Vector3(1, 3, FloorSize);
        wallLeft.transform.position = new Vector3(0, 1, FloorSize / 2); //modifié

        wallRight.transform.localScale = new Vector3(1, 3, FloorSize);
        wallRight.transform.position = new Vector3(FloorSize, 1, FloorSize / 2); //modifié
    }

    private void SpawnFood()
    {
        listFood = GameObject.FindGameObjectsWithTag("Food");
        timer += Time.deltaTime;

        if (listFood.Length < FoodNumber)
        {
            for (int i = 0; i < FoodNumber - listFood.Length; i++)
            {
                Vector3 randomSpawn = new Vector3(Random.Range(0, FloorSize), 1, Random.Range(0, FloorSize));
                Instantiate(food, randomSpawn, Quaternion.identity);
            }
        }
    }

    public void SpawnObstacles(){
        ListObstacle = GameObject.FindGameObjectsWithTag("Wall");
        
        for(int i = 0; i < 10; i++){
            Vector3 randomSpawn = new Vector3(Random.Range(0, FloorSize), 0.05f, Random.Range(0, FloorSize));
            Instantiate(Obstacle, randomSpawn, Quaternion.identity);
        }    
    }
    
    // getters and setters
    public float FloorSize { get => floorSize; set => floorSize = value; }
    public int FoodNumber { get => foodNumber; set => foodNumber = value; }
    public int StartingPopulation { get => startingPopulation; set => startingPopulation = value; }
}
