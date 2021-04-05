using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FPSShooting : MonoBehaviour
{
    private Camera mainCam;

    private float fireRate = 15f;
    private float nextTimeToFire = 0f;

    [SerializeField]
    private GameObject concrete_Impact;

    void Start()
    {
        mainCam = Camera.main;
    }

    // Update is called once per frame
    private void Update()
    {
        Shoot();
    }

    void Shoot()
    {
        if (Input.GetMouseButtonDown(0) && Time.time > nextTimeToFire)
        {
            nextTimeToFire = Time.time + 1f / fireRate;

            RaycastHit hit;
            print("SHOOT1");

            if (Physics.Raycast(mainCam.transform.position, mainCam.transform.forward, out hit))
            {
                Instantiate(concrete_Impact, hit.point, Quaternion.LookRotation(hit.normal));
                
            }
        }
    }


}
