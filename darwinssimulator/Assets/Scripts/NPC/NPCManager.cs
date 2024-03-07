using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;
using UnityEngine.UIElements;

public class NPCManager : MonoBehaviour
{
    public GameObject game;

    public LevelController levelController;

    public GameObject NPC;
    
    public List<NeatNetwork> allNetworks = new List<NeatNetwork>();
    public List<GameObject> allNPCs = new List<GameObject>();
    private List<NeatNetwork> bestNetworks = new List<NeatNetwork> ();

    private int inputNodes, outputNodes, hiddenNodes;

    private int npcsSpawned = 0;

    public int keepBest, leaveWorst;

    public int currentAlive;
    private int repopingLimit; 
    public bool spawnFromSave = false;
    public int bestTime = 100;
    public int addToBest = 50;
    private float bestNetworkDivider = 2;

    private float floorSize;

    private int startingPopulation;




    // Start is called before the first frame update
    void Start()
    {
        repopingLimit = levelController.repopingLimit;
        floorSize = levelController.FloorSize;

        inputNodes = 9;
        outputNodes = 2;
        hiddenNodes = 0;

        //InitialSpawnNPC();
    }

    // Update is called once per frame
    void Update()
    {
        // check si on est en jeu avant de repop des NPC
        if (game.GetComponent<GameScript>().gamePlaying == true)
        {
            repopingLimit = levelController.repopingLimit;
            repoping(); 
        }

    }

    private void repoping()
    {
        if (allNPCs.Count < repopingLimit)
        {
            if (bestNetworks.Count != 0)
            {
                for (int i = 0; i < repopingLimit - allNPCs.Count; i++)
                {
                    Vector3 randomSpawn = new Vector3(Random.Range(0, floorSize), 1, Random.Range(0, floorSize));
                    int random = (int)Random.Range(0, (bestNetworks.Count - 1)/bestNetworkDivider);
                    SpawnNpc(bestNetworks[random].MyGenome, randomSpawn);

                }
            }
            else
            {
                for (int i = 0; i < repopingLimit - allNPCs.Count; i++)
                {
                    Vector3 randomSpawn = new Vector3(Random.Range(0, floorSize), 1, Random.Range(0, floorSize));
                    SpawnNpc(randomSpawn);

                }
            }
        }
    }
    
    public void InitialSpawnNPC()
    {
        floorSize = levelController.FloorSize;
        startingPopulation = levelController.StartingPopulation;

        /* Creates Initial Group of NPC GameObjects from StartingPopulation int 
        and matches NPC Objects to their NetworkBrains. */

        for (int i = 0; i < startingPopulation; i++)
        {
            Vector3 randomSpawn = new Vector3(Random.Range(0, floorSize), 1, Random.Range(0, floorSize));
            SpawnNpc(randomSpawn);
            
        }
        MutatePopulation();
        MutatePopulation();
    }

    public void SpawnNpc(Vector3 position)
    // create a NPC with a network
    {
        NeatNetwork newNetwork = new(inputNodes, outputNodes, hiddenNodes, npcsSpawned);
        allNetworks.Add(newNetwork);
        
        GameObject newNPC = Instantiate(NPC, position, Quaternion.identity);
        newNPC.GetComponent<NpcController>().Id = npcsSpawned;
        newNPC.GetComponent<NpcController>().myNetwork = newNetwork;
        newNPC.GetComponent<NpcController>().InputNodes = inputNodes;
        newNPC.GetComponent<NpcController>().OutputNodes = outputNodes;
        
        allNPCs.Add(newNPC);

        npcsSpawned++;
    }

    public void SpawnNpc(NeatGenome genome, Vector3 position)
    {
        NeatNetwork newNetwork = new NeatNetwork(genome, npcsSpawned);
        allNetworks.Add(newNetwork);

        GameObject newNPC = Instantiate(NPC, position, Quaternion.identity);
        newNPC.GetComponent<NpcController>().Id = npcsSpawned;
        newNPC.GetComponent<NpcController>().myNetwork = newNetwork;
        newNPC.GetComponent<NpcController>().InputNodes = inputNodes;
        newNPC.GetComponent<NpcController>().OutputNodes = outputNodes;

        allNPCs.Add(newNPC);

        npcsSpawned++;
    }

    private void MutatePopulation()
    {
        foreach(GameObject NPC in allNPCs)
        {
            NPC.GetComponent<NpcController>().myNetwork.MutateNetwork();
        }

    }

    public void Death(float fitness, int id) 
    {
        // recupere le network
        NeatNetwork network = allNetworks.FirstOrDefault(obj => obj.Id == id);
        GameObject npc = allNPCs.FirstOrDefault(obj => obj.gameObject.GetComponent<NpcController>().Id == id);
        if (network != null)
        {
            network.fitness = fitness;
            CheckFitness(network);
            allNetworks.Remove(network);

        }
        if (npc != null)
        {
            allNPCs.Remove(npc);
            npc.GetComponent<NpcController>().DestroyNpc();
        }
    }

    private void CheckFitness(NeatNetwork network)
    {   

        if (bestNetworks.Count != 0)
        {
            for (int i = bestNetworks.Count - 1; i >= 0; i --)
            {
                if (bestNetworks[i].fitness < network.fitness)
                {
                    bestNetworks.Insert(i, network);
                    bestNetworks.RemoveAt(bestNetworks.Count - 1);
                }
            }
        }
        else
        {
            bestNetworks.Add(network);
        }
    }
}
