using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Linq;

public class ARFeatheredPlane : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        print("called this");
        Gaze.infos = FindObjectsOfType<InfoBehaviour>().ToList();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
