using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Vuforia;

public class projects : MonoBehaviour, IVirtualButtonEventHandler
{

    public GameObject vbButtonObject, name;

    // Start is called before the first frame update
    void Start()
    {

        vbButtonObject = GameObject.Find("Projects");
        vbButtonObject.GetComponent<VirtualButtonBehaviour>().RegisterEventHandler (this);
    

    }
    public void OnButtonPressed(VirtualButtonBehaviour vb)
    {
        Debug.Log("Button pressed");
        name.SetActive(true);

    }

    public void OnButtonReleased(VirtualButtonBehaviour vb)
    {

        Debug.Log("Starting the eleased");
        StartCoroutine(ExampleCoroutine());
    }

    IEnumerator ExampleCoroutine()
    {
        //Print the time of when the function is first called.
        Debug.Log("Started Coroutine at timestamp : " + Time.time);

        //yield on a new YieldInstruction that waits for 5 seconds.
        yield return new WaitForSeconds(5);


        name.SetActive(false);
    }
}


