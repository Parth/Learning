package paulscode.android.mupen64plus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

// TODO: Comment thoroughly
public class MenuSettingsActivity extends ListActivity
{
    public static MenuSettingsActivity mInstance = null;
    private OptionArrayAdapter optionArrayAdapter;  // array of menu options

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        mInstance = this;

        List<MenuOption>optionList = new ArrayList<MenuOption>();
        optionList.add( new MenuOption( "Video", "configure the graphics", "menuSettingsVideo" ) );
        optionList.add( new MenuOption( "Audio", "choose audio settings", "menuSettingsAudio" ) );
        optionList.add( new MenuOption( "Input", "map controller buttons", "menuSettingsInput" ) );
        optionList.add( new MenuOption( "Virtual Gamepad", "configure the virtual gamepad", "menuSkinsGamepad" ) );
        optionList.add( new MenuOption( "RSP", "rapid system prototyping", "menuSettingsRSP" ) );
        optionList.add( new MenuOption( "Core", "emulator core settings", "menuSettingsCore" ) );
        optionList.add( new MenuOption( "Reset Default Settings", "reset all to default values", "menuSettingsResetDefaults" ) );
        optionList.add( new MenuOption( "Restore App Data", "saves will be lost!", "menuSettingsRestoreAppData" ) );
        optionList.add( new MenuOption( "Enable Auto-Save", "automatically save game on exit",
                                        "menuSettingsAutoSave", Globals.auto_save ) );

        optionArrayAdapter = new OptionArrayAdapter( this, R.layout.menu_option, optionList );
        setListAdapter( optionArrayAdapter );
    }
    /*
     * Determines what to do, based on what option the user chose 
     * @param listView Used by Android.
     * @param view Used by Android.
     * @param position Which item the user chose.
     * @param id Used by Android.
     */
    @Override
    protected void onListItemClick( ListView listView, View view, int position, long id )
    {
        super.onListItemClick( listView, view, position, id );
        MenuOption menuOption = optionArrayAdapter.getOption( position );
        if( menuOption.info.equals( "menuSettingsVideo" ) )
        {
            Intent intent = new Intent( mInstance, MenuSettingsVideoActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuSettingsAudio" ) ) 
        {
            Intent intent = new Intent( mInstance, MenuSettingsAudioActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuSettingsInput" ) ) 
        {
            Intent intent = new Intent( mInstance, MenuSettingsInputActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuSkinsGamepad" ) )
        {
            Intent intent = new Intent( mInstance, MenuSkinsGamepadActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuSettingsRSP" ) ) 
        {
            Intent intent = new Intent( mInstance, MenuSettingsRSPActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuSettingsCore" ) ) 
        {
            Intent intent = new Intent( mInstance, MenuSettingsCoreActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuSettingsResetDefaults" ) ) 
        {
            if( !Updater.restoreDefaults( this ) )
            {
                Runnable toastMessager = new Runnable()
                {
                    public void run()
                    {
                        Toast toast = Toast.makeText( MenuActivity.mInstance, "Problem resetting defaults (see logcat)", Toast.LENGTH_LONG );
                        toast.setGravity( Gravity.BOTTOM, 0, 0 );
                        toast.show();
                    }
                };
                this.runOnUiThread( toastMessager );
                return;
            }
            mInstance.finish();
        }
        else if( menuOption.info.equals( "menuSettingsRestoreAppData" ) ) 
        {
            showDialog( Globals.SURE_ID );
        }
        else if( menuOption.info.equals( "menuSettingsAutoSave" ) ) 
        {
            Globals.auto_save = !Globals.auto_save;
            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.add( new MenuOption( "Enable Auto-Save", "automatically save game on exit",
                                                    "menuSettingsAutoSave", Globals.auto_save ) );
            MenuActivity.gui_cfg.put( "GENERAL", "auto_save", ( Globals.auto_save ? "1" : "0") );
        }
    }
    @Override
    protected Dialog onCreateDialog( int id )
    {
        switch( id )
        {
            case Globals.SURE_ID:
            {
                AlertDialog.Builder d = new AlertDialog.Builder( this );
                d.setTitle( "Are you sure?" );
                d.setIcon( R.drawable.icon );
                d.setPositiveButton( "Yes",
                    new DialogInterface.OnClickListener()
                    {
                        public void onClick( DialogInterface dialog, int which )
                        {
                            File appData = new File( Globals.DataDir );
                            SDLActivity.deleteFolder( appData );
                            Intent intent = new Intent( mInstance, MainActivity.class );
                            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
                            startActivity( intent );
                            mInstance.finish();
                            MenuActivity.mInstance.finish();
                        }
                    });
                d.setNegativeButton( "No", null );
                View v = LayoutInflater.from( this ).inflate( R.layout.about_dialog, null );
                TextView text = (TextView) v.findViewById( R.id.about_text );
                text.setText( "In-game saves, save-states, and texture packs will be lost!\n\n" +
                              "To back up your in-game saves and save-states, please choose the \"No\" button, exit the app, and navigate to the folder Android/data/paulscode.android.mupen64plus/data/save/ on your sdcard.  Copy the contents of this folder to somewhere else on your sdcard (NOT under paulscode.android.mupen64plus), or to your computer if connected.  To restore the backups after running \"Restore App Data\", copy the files back into Android/data/paulscode.android.mupen64plus/data/save/" );
                d.setView( v );
                return d.create();
            }
        }
        return( super.onCreateDialog( id ) );
    }
}

