package paulscode.android.mupen64plus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

// TODO: Comment thoroughly
public class MenuActivity extends ListActivity
{
    public static MenuActivity mInstance = null;
    private OptionArrayAdapter optionArrayAdapter;  // array of menu options
    private static NotificationManager notificationManager = null;

    public static Config mupen64plus_cfg = new Config( Globals.DataDir + "/mupen64plus.cfg" );
//    public static Config InputAutoCfg_ini = new Config( Globals.DataDir + "/data/InputAutoCfg.ini" );
    public static Config gui_cfg = new Config( Globals.DataDir + "/data/gui.cfg" );
    public static Config error_log = new Config( Globals.DataDir + "/error.log" );

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        mInstance = this;

        if( notificationManager == null )
            notificationManager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE );
        notificationManager.cancel( Globals.NOTIFICATION_ID );

        if( Globals.DataDir == null || Globals.DataDir.length() == 0 || !Globals.DataDirChecked )
        {
            Globals.PackageName = getPackageName();
            Globals.LibsDir = "/data/data/" + Globals.PackageName;
	    Globals.StorageDir = Globals.DownloadToSdcard ?
                Environment.getExternalStorageDirectory().getAbsolutePath() : getFilesDir().getAbsolutePath();

	    Globals.DataDir = Globals.DownloadToSdcard ?
                Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" +
                Globals.PackageName : getFilesDir().getAbsolutePath();
         
            Globals.DataDirChecked = true;

            mupen64plus_cfg = new Config( Globals.DataDir + "/mupen64plus.cfg" );
            //InputAutoCfg_ini = new Config( Globals.DataDir + "/data/InputAutoCfg.ini" );
            gui_cfg = new Config( Globals.DataDir + "/data/gui.cfg" );
            error_log = new Config( Globals.DataDir + "/error.log" );
        }

        String first_run = gui_cfg.get( "GENERAL", "first_run" );
        int width, height;

        Updater.checkFirstRun( this );
        Updater.checkv1_4( this );
        Updater.checkv1_5( this );
        Updater.checkv1_7( this );
        Updater.checkv1_8( this );
        Updater.checkv1_8_2( this );

/*        if( first_run != null && first_run.equals( "1" ) )
        { // this gets run the first time the app runs only!
            gui_cfg.put( "GENERAL", "first_run", "0" );
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics( metrics );
            if( metrics.widthPixels > metrics.heightPixels )
            {
                width = metrics.widthPixels;
                height = metrics.heightPixels;
            }
            else
            {
                width = metrics.heightPixels;
                height = metrics.widthPixels;
            }
            // pick a virtual gamepad layout based on screen resolution:
            if( width > 900 )
                gui_cfg.put( "GAME_PAD", "which_pad", "Mupen64Plus-AE-Analog-Tablet" );
            else if( width <= 320 )
                gui_cfg.put( "GAME_PAD", "which_pad", "Mupen64Plus-AE-Analog-Tiny" );
            else if( width < 854 )
                gui_cfg.put( "GAME_PAD", "which_pad", "Mupen64Plus-AE-Analog-Small" );
            else
                gui_cfg.put( "GAME_PAD", "which_pad", "Mupen64Plus-AE-Analog" );

            String romFolder;
            if( (new File( Globals.StorageDir + "/roms/n64" )).isDirectory() )
                romFolder = Globals.StorageDir + "/roms/n64";
            else
                romFolder = Globals.StorageDir;
            gui_cfg.put( "LAST_SESSION", "rom_folder", romFolder );
            gui_cfg.put( "GENERAL", "auto_save", "1" );
        }
*/

        String val = gui_cfg.get( "GAME_PAD", "redraw_all" );
        if( val != null )
            MenuSkinsGamepadActivity.redrawAll = ( val.equals( "1" ) ? true : false );
        val = gui_cfg.get( "KEYS", "disable_volume_keys" );
        if( val != null )  // remember the choice that was made about the volume keys
            Globals.volumeKeysDisabled = val.equals( "1" ) ? true : false;
        val = gui_cfg.get( "VIDEO_PLUGIN", "screen_stretch" );
        if( val != null )
            Globals.screen_stretch = ( val.equals( "1" ) ? true : false );
        val = gui_cfg.get( "VIDEO_PLUGIN", "auto_frameskip" );
        if( val != null )
            Globals.auto_frameskip = ( val.equals( "1" ) ? true : false );
        val = gui_cfg.get( "VIDEO_PLUGIN", "max_frameskip" );
        if( val != null )
        {
            try
            {  // make sure a valid integer was entered
                Globals.max_frameskip = Integer.valueOf( val ).intValue();
            }
            catch( NumberFormatException nfe )
            {}  // skip it if this happens
        }
        val = gui_cfg.get( "GENERAL", "auto_save" );
        if( val != null )
            Globals.auto_save = ( val.equals( "1" ) ? true : false );

        List<MenuOption>optionList = new ArrayList<MenuOption>();
        optionList.add( new MenuOption( "Choose Game", "select a game to play", "menuOpenROM" ) );

        val = gui_cfg.get( "LAST_SESSION", "rom" );
        if( val != null && val.length() > 0 )
            optionList.add( new MenuOption( "Resume", "continue your last game", "menuResume" ) );

        optionList.add( new MenuOption( "Settings", "configure plug-ins, skins, etc.", "menuSettings" ) );
        optionList.add( new MenuOption( "Help!", "paulscode.com support, report bugs", "menuHelp" ) );
        optionList.add( new MenuOption( "Credits", "developers, testers, contributers", "menuCredits" ) );
        //optionList.add( new MenuOption( "Skins", "customize the look", "menuSkins" ) );
        optionList.add( new MenuOption( "Close", "exit the app", "menuClose" ) );

        optionArrayAdapter = new OptionArrayAdapter( this, R.layout.menu_option, optionList );
        setListAdapter( optionArrayAdapter );

        Globals.errorMessage = error_log.get( "OPEN_ROM", "fail_crash" );
        if( Globals.errorMessage != null && Globals.errorMessage.length() > 0 )
        {
            Runnable toastMessager = new Runnable()
            {
                public void run()
                {
                    Toast toast = Toast.makeText( MenuActivity.mInstance, new String( Globals.errorMessage ), Toast.LENGTH_LONG );
                    toast.setGravity( Gravity.BOTTOM, 0, 0 );
                    toast.show();
                }
            };
            this.runOnUiThread( toastMessager );
        }
        error_log.put( "OPEN_ROM", "fail_crash", "" );
        error_log.save();
        Globals.errorMessage = null;
    }
    @Override
    public boolean onKeyDown( int keyCode, KeyEvent event )
    {
        if( keyCode == KeyEvent.KEYCODE_BACK )
            return true;
        return false;
    }
    @Override
    public boolean onKeyUp( int keyCode, KeyEvent event )
    {
        if( keyCode == KeyEvent.KEYCODE_BACK )
            return true;
        return false;
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
        if( menuOption.info.equals( "menuOpenROM" ) )
        {  // Open the file chooser to pick a ROM
            String path = gui_cfg.get( "LAST_SESSION", "rom_folder" );

            if( path == null || path.length() < 1 )
                FileChooserActivity.startPath = Globals.DataDir;
            else
                FileChooserActivity.startPath = path;
            FileChooserActivity.extensions = ".z64.v64.n64.zip";
            FileChooserActivity.parentMenu = null;
            FileChooserActivity.function = FileChooserActivity.FUNCTION_ROM;
            Intent intent = new Intent( mInstance, FileChooserActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuResume" ) ) 
        {  // Resume the last game
            File f = new File( Globals.StorageDir );
            if( !f.exists() )
            {
                Log.e( "MenuActivity", "SD Card not accessable in method onListItemClick (menuResume)" );
                Runnable toastMessager = new Runnable()
                {
                    public void run()
                    {
                        Toast toast = Toast.makeText( MenuActivity.mInstance, "App data not accessible (cable plugged in \"USB Mass Storage Device\" mode?)", Toast.LENGTH_LONG );
                        toast.setGravity( Gravity.BOTTOM, 0, 0 );
                        toast.show();
                    }
                };
                this.runOnUiThread( toastMessager );
                return;
            }
            mupen64plus_cfg.save();
            //InputAutoCfg_ini.save();
            gui_cfg.save();
            Globals.chosenROM = gui_cfg.get( "LAST_SESSION", "rom" );
            SDLActivity.resumeLastSession = true;
            Intent intent = new Intent( mInstance, SDLActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
            mInstance.finish();
            mInstance = null;
        }
        else if( menuOption.info.equals( "menuSettings" ) ) 
        {  // Configure the plug-ins
            Intent intent = new Intent( mInstance, MenuSettingsActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuHelp" ) ) 
        {  // Visit the FAQ page (opens browser)
            try
            {
                Intent browserIntent = new Intent( Intent.ACTION_VIEW, Uri.parse( "http://www.paulscode.com/forum/index.php?topic=197.msg3018#msg3018" ) );
                startActivity( browserIntent );
            }
            catch( Exception e )
            {
                Log.e( "MenuActivity", "Unable to open the FAQ page", e );
                Runnable toastMessager = new Runnable()
                {
                    public void run()
                    {
                        Toast toast = Toast.makeText( MenuActivity.mInstance, "Problem opening the browser, please report at paulscode.com", Toast.LENGTH_LONG );
                        toast.setGravity( Gravity.BOTTOM, 0, 0 );
                        toast.show();
                    }
                };
                this.runOnUiThread( toastMessager );
            }
        }
        else if( menuOption.info.equals( "menuCredits" ) ) 
        {  // Show the credits
            showDialog( Globals.ABOUT_ID );
        }
        else if( menuOption.info.equals( "menuClose" ) ) 
        {  // Shut down the app
            File f = new File( Globals.StorageDir );
            if( f.exists() )
            {
                mupen64plus_cfg.save();
                //InputAutoCfg_ini.save();
                gui_cfg.save();
            }
            mInstance.finish();
        }
    }
    @Override
    protected Dialog onCreateDialog( int id )
    {
        switch( id )
        {
            case Globals.ABOUT_ID:
            {
                AlertDialog.Builder d = new AlertDialog.Builder( this );
                d.setTitle( "Credits" );
                d.setIcon( R.drawable.icon );
                d.setNegativeButton( "Close", null );
                View v = LayoutInflater.from( this ).inflate( R.layout.about_dialog, null );
                TextView text = (TextView) v.findViewById( R.id.about_text );
                text.setText( "Developers:\n\n" +
                              "- Paul Lamb (ported everything to Android, developed front-end, maintenace, debugging)\n" +
                              "- Ari64 (created core ARM dynarec, provided porting/ debugging assistance.. an assembly savant!)\n" +
                              "- Kris (Metracity) (ported Rice Video plug-in to GLES2, assisted with debugging)\n" +
                              "- Pelya (ported components of SDL to Android, developed data downloader/ unpacker)\n" +
                              "- Epic_bubble (assisted with Xperia Play optimized content, researched useful open-source resources)\n" +
                              "- xperia64 (assisted with EEPROM clock emulation, also researched useful open-source resources)\n" +
                              "- cpasjuste (assisted with dynarec porting from 1.5 to 1.99.4)\n" +
                              "- GTan64 (provided alternate Mupen64Plus core port for comparison, assisted with SDL1.3 porting)\n" +
                              "- Yongzh (improved components of gles2n64, provided alternate Mupen64Plus core port for comparison)\n" +
                              "- ZodTTD (provided alternate gles2n64 port for comparison, helped track down a critical bug)\n" +
                              "- CZero (helped develop accurate octagon analog control algorithm)\n" +
                              "- BuckM (discovered the best analog control radius settings)\n" +
                              "- Orkin, Adventus (GLN64/gles2n64 video plug-in development, port to GLES2)\n" +
                              "- Rice (developed the famous Rice Video plug-in)\n" +
                              "- Hactarux, JttL, Ebenblues, Richard Goedeken, Richard42 (Mupen64/Mupen64Plus devs.. the real geniuses!)\n" +
                              "- (many more)\n\n" +
                              "\"Extreme\" Testers:\n\n" +
                              "- Epic_bubble\n" +
                              "- xperia64\n" +
                              "- scorpio16v\n" +
                              "- (way too many more to mention!)\n\n" +
                              "Artwork:\n\n" +
                              "- roymustang16 (virtual gamepad layouts)\n" +
                              "- shuy3n (icons, promotional graphics)\n\n" +
                              "Miscellaneous:\n\n" +
                              "- Epic_bubble (promotional video)\n" +
                              "- Potticus (compatibility wiki)" );
                d.setView( v );
                return d.create();
            }
        }
        return( super.onCreateDialog( id ) );
    }
}

