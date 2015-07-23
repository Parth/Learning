package paulscode.android.mupen64plus;

import java.io.File; 
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


// TODO: Comment thoroughly
public class MenuSettingsVideoConfigureActivity extends ListActivity implements IFileChooser
{
    public static MenuSettingsVideoConfigureActivity mInstance = null;
    private OptionArrayAdapter optionArrayAdapter;  // array of menu options
    private Config gles2n64_conf;
    private boolean enableFog = false;
    private boolean enableTribuffer = false;
    private boolean forceScreenClear = true;
    private boolean alphaTest = true;

    private boolean isRice = false;
    private boolean riceSkipFrame = false;
    private boolean riceFastTextureCRC = true;
    private boolean riceFastTexture = false;
    private boolean riceHiResTextures = true;
    private boolean hackZ = false;

//    private String texturePack = null;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        mInstance = this;

        // TODO: standardize this, using the gui config.  This is too much of a hack.
        isRice = false;
        String filename = MenuActivity.mupen64plus_cfg.get( "UI-Console", "VideoPlugin" );
        if( filename != null )
        {
            filename = filename.replace( "\"", "" );
            int x = filename.lastIndexOf( "/" );
            if( x > -1 && x < (filename.length() - 1) )
            {
                String currentPlugin = filename.substring( x + 1, filename.length() );
                if( currentPlugin != null && currentPlugin.equals( "libgles2rice.so" ) )
                    isRice = true;
            }
        }
        //

        gles2n64_conf = new Config( Globals.DataDir + "/data/gles2n64.conf" );
        String val = gles2n64_conf.get( "[<sectionless!>]", "enable fog" );

        if( val != null )
            enableFog = ( val.equals( "1" ) ? true : false );

        val = gles2n64_conf.get( "[<sectionless!>]", "force screen clear" );
        if( val != null )
            forceScreenClear = ( val.equals( "1" ) ? true : false );

        val = gles2n64_conf.get( "[<sectionless!>]", "enable alpha test" );
        if( val != null )
            alphaTest = ( val.equals( "1" ) ? true : false );

        val = gles2n64_conf.get( "[<sectionless!>]", "tribuffer opt" );
        if( val != null )
            enableTribuffer = ( val.equals( "1" ) ? true : false );
        val = gles2n64_conf.get( "[<sectionless!>]", "hack z" );
        if( val != null )
            hackZ = ( val.equals( "1" ) ? true : false );

        val = MenuActivity.mupen64plus_cfg.get( "Video-Rice", "SkipFrame" );
        if( val != null )
            riceSkipFrame = ( val.equals( "1" ) ? true : false );
        val = MenuActivity.mupen64plus_cfg.get( "Video-Rice", "FastTextureCRC" );
        if( val != null )
            riceFastTextureCRC = ( val.equals( "1" ) ? true : false );
        val = MenuActivity.mupen64plus_cfg.get( "Video-Rice", "FastTextureLoading" );
        if( val != null )
            riceFastTexture = ( val.equals( "1" ) ? true : false );
        val = MenuActivity.mupen64plus_cfg.get( "Video-Rice", "LoadHiResTextures" );
        if( val != null )
            riceHiResTextures = ( val.equals( "1" ) ? true : false );

        List<MenuOption>optionList = new ArrayList<MenuOption>();
      if( isRice )
      {
        optionList.add( new MenuOption( "Enable Skip Frame", "(improves speed at cost of FPS)",
                                        "menuSettingsVideoConfigureSkipFrame", riceSkipFrame ) );
        optionList.add( new MenuOption( "Enable Fast Texture CRC", "(disabling improves 2D elements)",
                                        "menuSettingsVideoConfigureFastTextureCRC", riceFastTextureCRC ) );
        optionList.add( new MenuOption( "Enable Fast Texture Loading", "(fixes circle transitions)",
                                        "menuSettingsVideoConfigureFastTexture", riceFastTexture ) );
        optionList.add( new MenuOption( "Enable HiRes Textures", "(use imported texture packs)",
                                        "menuSettingsVideoConfigureUseHiResTextures", riceHiResTextures ) );
        optionList.add( new MenuOption( "Import Texture Pack, ZIP format", "(apply custom textures to a game)",
                                        "menuSettingsVideoConfigureImportHiResTextures" ) );
      }
      else
      {
        optionList.add( new MenuOption( "Stretch Screen", "(may skew aspect ratio)",
                                        "menuSettingsVideoConfigureStretch", Globals.screen_stretch ) );
        optionList.add( new MenuOption( "Auto Frameskip", "(auto-adjusts based on speed)",
                                        "menuSettingsVideoConfigureAutoFrameskip", Globals.auto_frameskip ) );
        optionList.add( new MenuOption( "Max Frameskip", "=" + Globals.max_frameskip + " (disable auto frameskip to set manually)",
                                        "menuSettingsVideoConfigureMaxFrameskip" ) );
        optionList.add( new MenuOption( "Enable Fog", "(needs work)",
                                        "menuSettingsVideoConfigureFog", enableFog ) );
        optionList.add( new MenuOption( "Enable Tribuffer Opt", "(disable if Zelda crashes on startup)",
                                        "menuSettingsVideoConfigureTribuffer", enableTribuffer ) );
        optionList.add( new MenuOption( "Force Screen Clear", "(clears some junk graphics)",
                                        "menuSettingsVideoConfigureScreenClear", forceScreenClear ) );
        optionList.add( new MenuOption( "Enable Alpha Test", "(disable for speed hack, black textures)",
                                        "menuSettingsVideoConfigureAlpha", alphaTest ) );
        optionList.add( new MenuOption( "Hack Z", "(enable to fix flashing background)",
                                        "menuSettingsVideoConfigureHackZ", hackZ ) );
      }
      optionArrayAdapter = new OptionArrayAdapter( this, R.layout.menu_option, optionList );
      setListAdapter( optionArrayAdapter );
    }

    public void fileChosen( String filename )
    {
/*
        if( texturePack == null )
        {
            texturePack = filename;
            showToast( "Choose ROM for these textures" );
            new Thread()
            {
                public void run()
                {
                    for( int x = 0; (x < 100) && (FileChooserActivity.mInstance != null); x++ )
                    {  // wait for previous FileChooserActivity to finish
                        try{ Thread.sleep( 20 ); }catch( InterruptedException ie ){}
                    }
                    String path = MenuActivity.gui_cfg.get( "LAST_SESSION", "rom_folder" );
                    if( path == null || path.length() < 1 )
                        FileChooserActivity.startPath = Globals.StorageDir;
                    else
                        FileChooserActivity.startPath = path;
                    FileChooserActivity.extensions = ".z64.v64.n64.zip";
                    FileChooserActivity.parentMenu = mInstance;
                    Intent intent = new Intent( mInstance, FileChooserActivity.class );
                    intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
                    startActivity( intent );
                }
            }.start();
        }
        else
        {
            String headerName = SDLActivity.getHeaderName( filename );

            if( headerName == null )
            {
                headerName = MenuActivity.error_log.get( "READ_HEADER", "fail" );
                MenuActivity.error_log.put( "READ_HEADER", "fail", "" );
            }
            if( headerName == null )
            {
                texturePack = null;
                return;
            }
*/
        String headerName = SDLActivity.getTexturePackName( filename );
        if( headerName == null )
        {
            if( Globals.errorMessage != null && Globals.errorMessage.length() > 0 )
                showToast( Globals.errorMessage );
            Globals.errorMessage = null;
            return;
        }
        String outputFolder = Globals.DataDir + "/data/hires_texture/" + headerName;
        SDLActivity.deleteFolder( new File( outputFolder ) );
        SDLActivity.unzipAll( new File( filename ), outputFolder );
//            showToast( headerName );
//            texturePack = null;
//        }
    }

    private void showToast( String message )
    {
        final String toastMessage = message;
        Runnable toastMessager = new Runnable()
        {
            public void run()
            {
                Toast toast = Toast.makeText( mInstance, toastMessage, Toast.LENGTH_LONG );
                toast.setGravity( Gravity.BOTTOM, 0, 0 );
                toast.show();
            }
        };
        this.runOnUiThread( toastMessager );
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
        if( menuOption.info.equals( "menuSettingsVideoConfigureStretch" ) ) 
        {
            Globals.screen_stretch = !Globals.screen_stretch;
            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Stretch Screen", "(may skew aspect ratio)",
                                           "menuSettingsVideoConfigureStretch", Globals.screen_stretch ), position );
            MenuActivity.gui_cfg.put( "VIDEO_PLUGIN", "screen_stretch", Globals.screen_stretch ? "1" : "0" );
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureAutoFrameskip" ) ) 
        {
            Globals.auto_frameskip = !Globals.auto_frameskip;
            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Auto Frameskip", "(gles2n64 only)",
                                           "menuSettingsVideoConfigureAutoFrameskip", Globals.auto_frameskip ), position );
            MenuActivity.gui_cfg.put( "VIDEO_PLUGIN", "auto_frameskip", Globals.auto_frameskip ? "1" : "0" );
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureMaxFrameskip" ) ) 
        {
            Globals.max_frameskip++;
            if( Globals.max_frameskip > 5 )
                Globals.max_frameskip = 0;
            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Max Frameskip", "=" + 
                                                    Globals.max_frameskip + " (disable auto frameskip to set manually)",
                                                    "menuSettingsVideoConfigureMaxFrameskip" ), position );
            MenuActivity.gui_cfg.put( "VIDEO_PLUGIN", "max_frameskip", "" + Globals.max_frameskip );
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureFog" ) ) 
        {
            enableFog = !enableFog;

            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Enable Fog", "(needs work)",
                                        "menuSettingsVideoConfigureFog", enableFog ), position );
            gles2n64_conf.put( "[<sectionless!>]", "enable fog", (enableFog ? "1" : "0") );
            gles2n64_conf.save();
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureTribuffer" ) ) 
        {
            enableTribuffer = !enableTribuffer;

            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Enable Tribuffer Opt",
                                                       "(disable if Zelda crashes on startup)",
                                                       "menuSettingsVideoConfigureTribuffer",
                                                       enableTribuffer ), position );
            gles2n64_conf.put( "[<sectionless!>]", "tribuffer opt", (enableTribuffer ? "1" : "0") );
            gles2n64_conf.save();
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureScreenClear" ) ) 
        {
            forceScreenClear = !forceScreenClear;

            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Force Screen Clear", "(clears some junk graphics)",
                                        "menuSettingsVideoConfigureScreenClear", forceScreenClear ), position );
            gles2n64_conf.put( "[<sectionless!>]", "force screen clear", (forceScreenClear ? "1" : "0") );
            gles2n64_conf.save();
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureAlpha" ) ) 
        {
            alphaTest = !alphaTest;

            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Enable Alpha Test", "(disable for speed hack, black textures)",
                                        "menuSettingsVideoConfigureAlpha", alphaTest ), position );
            gles2n64_conf.put( "[<sectionless!>]", "enable alpha test", (alphaTest ? "1" : "0") );
            gles2n64_conf.save();
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureHackZ" ) ) 
        {
            hackZ = !hackZ;

            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.add( new MenuOption( "Hack Z", "(enable to fix flashing background)",
                                        "menuSettingsVideoConfigureHackZ", hackZ ) );
            gles2n64_conf.put( "[<sectionless!>]", "hack z", (hackZ ? "1" : "0") );
            gles2n64_conf.save();
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureSkipFrame" ) ) 
        {
            riceSkipFrame = !riceSkipFrame;

            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Enable Skip Frame", "(improves speed at cost of FPS)",
                                                  "menuSettingsVideoConfigureSkipFrame", riceSkipFrame ), position );
            MenuActivity.mupen64plus_cfg.put( "Video-Rice", "SkipFrame", (riceSkipFrame ? "1" : "0") );
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureFastTextureCRC" ) ) 
        {
            riceFastTextureCRC = !riceFastTextureCRC;

            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Enable Fast Texture CRC", "(disabling improves 2D elements)",
                                        "menuSettingsVideoConfigureFastTextureCRC", riceFastTextureCRC ), position );
            MenuActivity.mupen64plus_cfg.put( "Video-Rice", "FastTextureCRC", (riceFastTextureCRC ? "1" : "0") );
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureFastTexture" ) ) 
        {
            riceFastTexture = !riceFastTexture;

            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Enable Fast Texture Loading", "(fixes circle transitions)",
                                        "menuSettingsVideoConfigureFastTexture", riceFastTexture ), position );
            MenuActivity.mupen64plus_cfg.put( "Video-Rice", "FastTextureLoading", (riceFastTexture ? "1" : "0") );
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureUseHiResTextures" ) ) 
        {
            riceHiResTextures = !riceHiResTextures;

            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Enable HiRes Textures", "(use imported texture packs)",
                                        "menuSettingsVideoConfigureUseHiResTextures", riceHiResTextures ), position );
            MenuActivity.mupen64plus_cfg.put( "Video-Rice", "LoadHiResTextures", (riceHiResTextures ? "1" : "0") );
        }
        else if( menuOption.info.equals( "menuSettingsVideoConfigureImportHiResTextures" ) ) 
        {
//            texturePack = null;
//            showToast( "Choose texture pack" );
            String path = MenuActivity.gui_cfg.get( "LAST_SESSION", "texture_folder" );

            if( path == null || path.length() < 1 )
                FileChooserActivity.startPath = Globals.StorageDir;
            else
                FileChooserActivity.startPath = path;
            FileChooserActivity.extensions = ".zip";
            FileChooserActivity.parentMenu = mInstance;
            FileChooserActivity.function = FileChooserActivity.FUNCTION_TEXTURE;
            Intent intent = new Intent( mInstance, FileChooserActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
    }
}
