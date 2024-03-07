using System.Collections;
using System.Collections.Generic;
using UnityEngine.Windows;

public class Node
{
    public int id;
    public float value;
    public List<Connection> inputConnections;
    public List<Connection> outputConnections;
    private Activations.actFunc nodeActivation;


    public Node(int ident)
    {
        id = ident;
        inputConnections = new List<Connection>();
        outputConnections = new List<Connection>();
    }

    public void SetNodeActivation(Activations.actFunc act)
    {
        NodeActivation = act;
    }
    
    
    public void SetInputNodeValue(float input)
    {
        value = NodeActivation(input);
    }
    
    public void SetNodeValue()
    {
        float val = 0;
        foreach (Connection con in inputConnections)
        {
            val += (con.weight * con.inputNodeValue);
        }
        value = NodeActivation(val);

    }

    public void FeedForwardValue()
    {
        foreach (Connection con in outputConnections)
        {
            con.inputNodeValue = value;
        }
    }
    // getters and setters
    public Activations.actFunc NodeActivation { get => nodeActivation; set => nodeActivation = value; }


}
