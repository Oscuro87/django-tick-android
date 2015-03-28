package org.ec.androidticket.backend.managers;

import android.content.Context;
import android.util.Log;

import org.ec.androidticket.backend.GlobalSettings;
import org.ec.androidticket.backend.models.CredentialCookie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CookieManager
{
    /**
     * Écrit un cookie permettant de ne pas devoir rentrer son login à chaque fois.
     * Ne fonctionne que si l'option "remember me" est active!
     */
    public static void writeCookie(Context applicationContext, String email, String password, boolean remember)
    {
        if (remember)
        {
            try
            {
                File cookie = new File(applicationContext.getCacheDir(), GlobalSettings.CREDENTIAL_COOKIE_FILENAME);
                if (!cookie.exists())
                    cookie.createNewFile();
                FileWriter writer = new FileWriter(cookie);
                writer.append(email);
                writer.append(" ");
                // TODO: MD5 le mdp?
                writer.append(password);
                writer.flush();
                writer.close();
            } catch (IOException e)
            {
                Log.e("CustomLog", e.getMessage());
            }

        } else
        {
            // On détruit le cookie car l'utilisateur ne veut plus être 'enregistré'
            destroyCookie(applicationContext);
        }
    }

    /**
     * Détruit un cookie qui n'est plus nécéssaire.
     */
    public static void destroyCookie(Context applicationContext)
    {
        File cookie = new File(applicationContext.getCacheDir(),
                GlobalSettings.CREDENTIAL_COOKIE_FILENAME);
        if (cookie.exists())
            cookie.delete();
    }

    /**
     * Retourne les informations de cookie.
     *
     * @return Un array sous la forme ["email", "mot de passe"].
     */
    public static CredentialCookie readCookie(Context applicationContext)
    {
        File cookieFile = new File(applicationContext.getCacheDir(), GlobalSettings.CREDENTIAL_COOKIE_FILENAME);
        if (!cookieFile.exists())
            return null;
        else
        {
            try
            {
                String email;
                String password;
                InputStream in = new FileInputStream(cookieFile);
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader breader = new BufferedReader(reader);

                String line = breader.readLine();
                breader.close();
                email = line.split(" ")[0];
                password = line.split(" ")[1];

                return new CredentialCookie(email, password);
            } catch (FileNotFoundException e)
            {
                Log.e("CustomLog", e.getMessage());
                return null;
            } catch (IOException e)
            {
                Log.e("CustomLog", e.getMessage());
                return null;
            }
        }
    }
}
