package paulscode.android.mupen64plus;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

// TODO: Comment thoroughly
public class MenuSettingsInputConfigureButtonsActivity extends ListActivity implements IScancodeListener
{
    public static MenuSettingsInputConfigureButtonsActivity mInstance = null;
    private OptionArrayAdapter optionArrayAdapter;  // array of menu options
    private ScancodeDialog scancodeDialog = null;
    public static int controllerNum = -1;
    public static boolean plugged = true;
    public static String plugin = "Mem pak";

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        mInstance = this;

        List<MenuOption>optionList = new ArrayList<MenuOption>();

        // TODO: remove (function in the background)
        optionList.add( new MenuOption( "Disable Volume Keys", "use as controller buttons", "menuSettingsInputConfigureVolume",
                                        Globals.volumeKeysDisabled ) );

        if( controllerNum > 0 && controllerNum < 5 )
        {
            if( controllerNum == 1 )
                plugged = true;
            else
                plugged = false;

            String val = MenuActivity.mupen64plus_cfg.get( "Input-SDL-Control" + controllerNum, "plugged" );
            if( val != null )
                plugged = ( val.equals( "True" ) ? true : false );

            optionList.add( new MenuOption( "Plugged In", "Connect Controller " + controllerNum, "plugged", plugged ) );

            val = MenuActivity.mupen64plus_cfg.get( "Input-SDL-Control" + controllerNum, "plugin" );
            if( val != null )
            {
                if( val.equals( "1" ) )
                    plugin = "None";
                else if( val.equals( "5" ) )
                    plugin = "Rumble pak";
                else
                    plugin = "Mem pak";
            }

            optionList.add( new MenuOption( "Expansion", plugin, "plugin" ) );
        }

        addOption( optionList, "D-pad Right", "DPad R" );
        addOption( optionList, "D-pad Left", "DPad L" );
        addOption( optionList, "D-pad Down", "DPad D" );
        addOption( optionList, "D-pad Up", "DPad U" );
        addOption( optionList, "Start", "Start" );
        addOption( optionList, "Z", "Z Trig" );
        addOption( optionList, "B", "B Button" );
        addOption( optionList, "A", "A Button" );
        addOption( optionList, "C-pad Right", "C Button R" );
        addOption( optionList, "C-pad Left", "C Button L" );
        addOption( optionList, "C-pad Down", "C Button D" );
        addOption( optionList, "C-pad Up", "C Button U" );
        addOption( optionList, "R", "R Trig" );
        addOption( optionList, "L", "L Trig" );
        addOption( optionList, "Mempak Switch", "Mempak switch" );
        addOption( optionList, "Rumblepak Switch", "Rumblepak switch" );
        addOption( optionList, "Analog Right", "X Axis2" );
        addOption( optionList, "Analog Left", "X Axis1" );
        addOption( optionList, "Analog Down", "Y Axis2" );
        addOption( optionList, "Analog Up", "Y Axis1" );

//        optionList.add( new MenuOption( "--SPECIAL FUNCTIONS--", "", "line" ) );

        addCoreOption( optionList, "Stop", "Kbd Mapping Stop" );
        addCoreOption( optionList, "Save State", "Kbd Mapping Save State" );
        addCoreOption( optionList, "Load State", "Kbd Mapping Load State" );
        addCoreOption( optionList, "Increment Slot", "Kbd Mapping Increment Slot" );
        addCoreOption( optionList, "Reset", "Kbd Mapping Reset" );
        addCoreOption( optionList, "Speed Down", "Kbd Mapping Speed Down" );
        addCoreOption( optionList, "Speed Up", "Kbd Mapping Speed Up" );
        addCoreOption( optionList, "Pause", "Kbd Mapping Pause" );
        addCoreOption( optionList, "Fast Forward", "Kbd Mapping Fast Forward" );
        addCoreOption( optionList, "Frame Advance", "Kbd Mapping Frame Advance" );
        addCoreOption( optionList, "Gameshark", "Kbd Mapping Gameshark" );

        optionArrayAdapter = new OptionArrayAdapter( this, R.layout.menu_option, optionList );
        setListAdapter( optionArrayAdapter );
    }

    public void addCoreOption( List<MenuOption> optionList, String name, String info )
    {
        if( controllerNum > 0 || info == null )
            return;

        int scancode = 0;
        String val = MenuActivity.mupen64plus_cfg.get( "Core", info );
        if( val != null )
        {
            try
            {  // make sure a valid integer was entered
                scancode = Integer.valueOf( val ).intValue();
            }
            catch( NumberFormatException nfe )
            {}  // skip it if this happens
        }
        optionList.add( new MenuOption( name,
                                        ((scancode > 0) ? ("keycode " + scancode) : "(not mapped)"),
                                        info ) );
    }

    public void addOption( List<MenuOption> optionList, String name, String info )
    {
        if( controllerNum < 1 || controllerNum > 4 || info == null )
            return;

        int scancode = 0;
        String val;

        if( info.contains( "Axis" ) )
            val = MenuActivity.mupen64plus_cfg.get( "Input-SDL-Control" + controllerNum,
                                                    info.substring( 0, info.length() - 1 ) );
        else
            val = MenuActivity.mupen64plus_cfg.get( "Input-SDL-Control" + controllerNum, info.substring( 0, info.length() ) );

        if( val == null )
            return;

        int x = val.indexOf( "(" );
        int y = val.indexOf( ")" );
        if( x < 0 || y < 0 || y <= x )
            return;
        val = val.substring( x + 1, y ).trim();

        if( val == null || val.length() < 1 )
            return;

        if( info.contains( "Axis" ) )
        {
            x = val.indexOf( "," );
            if( x < 0 )
                return;
            try
            {  // make sure a valid integer was entered
                if( info.contains( "Axis1" ) )
                {
                    scancode = Integer.valueOf( val.substring( 0, x ) ).intValue();
                }
                else
                {
                    scancode = Integer.valueOf( val.substring( x + 1, val.length() ) ).intValue();
                }
            }
            catch( NumberFormatException nfe )
            {}  // skip it if this happens
        }
        else
        {
            try
            {  // make sure a valid integer was entered
                scancode = Integer.valueOf( val ).intValue();
            }
            catch( NumberFormatException nfe )
            {}  // skip it if this happens
        }

        optionList.add( new MenuOption( name,
                                        ((scancode > 0) ? ("keycode " + scancode) : "(not mapped)"),
                                        info ) );
    }

    public void returnCode( int scancode, int codeType )
    {
        if( codeType == 1 )
        {
            MenuActivity.mupen64plus_cfg.put( "Core", ScancodeDialog.menuItemInfo, "" + scancode );
            optionArrayAdapter.remove( optionArrayAdapter.getOption( ScancodeDialog.menuItemPosition ) );
            optionArrayAdapter.insert( new MenuOption( ScancodeDialog.menuItemName, "keycode " + scancode,
                                       ScancodeDialog.menuItemInfo ), ScancodeDialog.menuItemPosition );
            return;
        }

        String param = ScancodeDialog.menuItemInfo;
        if( param == null )
            return;
        param = param.trim();
        String val;

        if( param.contains( "Axis" ) )
            val = MenuActivity.mupen64plus_cfg.get( "Input-SDL-Control" + controllerNum,
                                                    param.substring( 0, param.length() - 1 ) );
        else
            val = MenuActivity.mupen64plus_cfg.get( "Input-SDL-Control" + controllerNum,
                                                    param.substring( 0, param.length() ) );

        if( val == null )
            return;

        int x = val.indexOf( "(" );
        int y = val.indexOf( ")" );
        if( x < 0 || y < 0 || y <= x )
            return;

        val = val.substring( x + 1, y ).trim();

        if( param.contains( "Axis" ) )
        {
            x = val.indexOf( "," );
            if( x < 0 )
                return;
            if( param.contains( "Axis1" ) )
                val = "(" + scancode + "," + val.substring( x + 1, val.length() ) + ")";
            else
                val = "(" + val.substring( 0, x ) + "," + scancode + ")";
            MenuActivity.mupen64plus_cfg.put( "Input-SDL-Control" + controllerNum,
                                              param.substring( 0, param.length() - 1 ), "key" + val );
        }
        else
        {
            val = "(" + scancode + ")";
            MenuActivity.mupen64plus_cfg.put( "Input-SDL-Control" + controllerNum, param, "key" + val );
        }
        optionArrayAdapter.remove( optionArrayAdapter.getOption( ScancodeDialog.menuItemPosition ) );
        optionArrayAdapter.insert( new MenuOption( ScancodeDialog.menuItemName,
                                                   ((scancode > 0) ? ("keycode " + scancode) : "(not mapped)"),
                                                   param ),
                                   ScancodeDialog.menuItemPosition );
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
        if( scancodeDialog == null )
            scancodeDialog = new ScancodeDialog( mInstance );

        if( menuOption.info.equals( "menuSettingsInputConfigureVolume" ) ) 
        {
            Globals.volumeKeysDisabled = !Globals.volumeKeysDisabled;
            MenuActivity.gui_cfg.put( "KEYS", "disable_volume_keys", Globals.volumeKeysDisabled ? "1" : "0" );
            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Disable Volume Keys", "use as controller buttons",
                                           "menuSettingsInputConfigureVolume", Globals.volumeKeysDisabled ), position );
        }
        else if( menuOption.info.equals( "plugged" ) ) 
        {
            plugged = !plugged;

            MenuActivity.mupen64plus_cfg.put( "Input-SDL-Control" + controllerNum, "plugged", plugged ? "True" : "False" );
            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Plugged In", "Enable to connect Controller " + controllerNum,
                                                       "plugged", plugged ), position );
        }
        else if( menuOption.info.equals( "plugin" ) ) 
        {
            String p = "2";
            if( plugin.equals( "Mem pak" ) )
            {
                plugin = "Rumble pak";
                p = "5";
            }
            else if( plugin.equals( "Rumble pak" ) )
            {
                plugin = "None";
                p = "1";
            }
            else
                plugin = "Mem pak";
            MenuActivity.mupen64plus_cfg.put( "Input-SDL-Control" + controllerNum, "plugin", p );
            optionArrayAdapter.remove( menuOption );
            optionArrayAdapter.insert( new MenuOption( "Expansion", plugin, "plugin" ), position );
        }
        else if( !menuOption.info.equals( "line" ) )
        {
            ScancodeDialog.parent = this;

            if
            (   menuOption.info.equals( "Kbd Mapping Stop" ) ||
                menuOption.info.equals( "Kbd Mapping Save State" ) ||
                menuOption.info.equals( "Kbd Mapping Load State" ) ||
                menuOption.info.equals( "Kbd Mapping Increment Slot" ) ||
                menuOption.info.equals( "Kbd Mapping Reset" ) ||
                menuOption.info.equals( "Kbd Mapping Speed Down" ) ||
                menuOption.info.equals( "Kbd Mapping Speed Up" ) ||
                menuOption.info.equals( "Kbd Mapping Pause" ) ||
                menuOption.info.equals( "Kbd Mapping Fast Forward" ) ||
                menuOption.info.equals( "Kbd Mapping Frame Advance" ) ||
                menuOption.info.equals( "Kbd Mapping Gameshark" )
            )
                ScancodeDialog.codeType = 1;
            else
                ScancodeDialog.codeType = 0;

            ScancodeDialog.menuItemName = menuOption.name;
            ScancodeDialog.menuItemInfo = menuOption.info;
            ScancodeDialog.menuItemPosition = position;
            scancodeDialog.show();
        }
    }
}
