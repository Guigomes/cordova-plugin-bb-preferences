package br.com.bb.plugins;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by f3861879 on 08/03/17.
 */

public class PreferencesPlugin extends CordovaPlugin {

    SharedPreferences sharedPrefs;
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        sharedPrefs = cordova.getActivity().getPreferences(Context.MODE_PRIVATE);
        super.initialize(cordova, webView);

    }


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("put")) {
            String chave = args.getString(0);
            String valor = args.getString(1);
            if(salvarPreferencia(chave, valor)) {
                callbackContext.success(valor);
            }else {
                callbackContext.error("Falha ao salvar preferência");
            }
        }else if (action.equals("get")) {
            String chave = args.getString(0);
            String valor = sharedPrefs.getString(chave, "");
            if(!valor.isEmpty()){
                callbackContext.success(valor);
            }else{
                callbackContext.error("Falha ao recuperar preferência");
            }

            return true;
        }
        return false;
    }



    public boolean salvarPreferencia(String chave, boolean valor){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(chave, valor);
        editor.commit();
        return true;

    }

    public boolean salvarPreferencia(String chave, int valor){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(chave, valor);
        editor.commit();
        return true;
    }
    public boolean salvarPreferencia(String chave, String valor){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(chave, valor);
        editor.commit();
        return true;
    }

    private void echo(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message + " - Funcionou");
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
