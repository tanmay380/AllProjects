using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FPSHandsWeapon : MonoBehaviour
{
    public AudioClip shootclip, reloadclip;
    private AudioSource audioManager;
    private GameObject muzzleFlash;

    private Animator anim;

    private string SHOOT = "Shoot";
    private string RELOAD = "Reload";
    // Start is called before the first frame update
    void Awake()
    {
        muzzleFlash = transform.Find("MuzzleFlash").gameObject;
        muzzleFlash.SetActive(false);

        audioManager = GetComponent<AudioSource>();
        anim = GetComponent<Animator>();
    }

    public void Shoot()
    {
        if( audioManager.clip!= shootclip)
        {
            audioManager.clip = shootclip;
        }
        audioManager.Play();
        StartCoroutine(TurnMuzzleFlashOn());

        anim.SetTrigger(SHOOT);

    }

    IEnumerator TurnMuzzleFlashOn()
    {
        muzzleFlash.SetActive(true);
        yield return new WaitForSeconds(0.05f);
        muzzleFlash.SetActive(false);
    }

    public void Reload()
    {
        StartCoroutine(PlayReloadSound());
        anim.SetTrigger(RELOAD);

    }

    IEnumerator PlayReloadSound()
    {
        //FPSController.isReloading = false;
        yield return new WaitForSeconds(0.8f);
        if (audioManager.clip != reloadclip)
        {
            audioManager.clip = reloadclip;
        }
        audioManager.Play();
        
    }

}//class
