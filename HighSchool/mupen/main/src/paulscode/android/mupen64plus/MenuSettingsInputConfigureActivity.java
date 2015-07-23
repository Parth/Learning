package paulscode.android.mupen64plus;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

// TODO: Comment thoroughly
public class MenuSettingsInputConfigureActivity extends ListActivity
{
    public static MenuSettingsInputConfigureActivity mInstance = null;
    private OptionArrayAdapter optionArrayAdapter;  // array of menu options

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        mInstance = this;

        List<MenuOption>optionList = new ArrayList<MenuOption>();
        optionList.add( new MenuOption( "Controller 1", "map controller 1 buttons", "menuSettingsInputConfigureController1" ) );
        optionList.add( new MenuOption( "Controller 2", "map controller 2 buttons", "menuSettingsInputConfigureController2" ) );
        optionList.add( new MenuOption( "Controller 3", "map controller 3 buttons", "menuSettingsInputConfigureController3" ) );
        optionList.add( new MenuOption( "Controller 4", "map controller 4 buttons", "menuSettingsInputConfigureController4" ) );
        optionList.add( new MenuOption( "Special Buttons", "map core functions to buttons", "menuSettingsInputConfigureCoreFunctions" ) );

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
        if( menuOption.info.equals( "menuSettingsInputConfigureController1" ) )
        {  // open the menu to map controller buttons
            MenuSettingsInputConfigureButtonsActivity.controllerNum = 1;
            Intent intent = new Intent( mInstance, MenuSettingsInputConfigureButtonsActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuSettingsInputConfigureController2" ) )
        {  // open the menu to map controller buttons
            MenuSettingsInputConfigureButtonsActivity.controllerNum = 2;
            Intent intent = new Intent( mInstance, MenuSettingsInputConfigureButtonsActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuSettingsInputConfigureController3" ) )
        {  // open the menu to map controller buttons
            MenuSettingsInputConfigureButtonsActivity.controllerNum = 3;
            Intent intent = new Intent( mInstance, MenuSettingsInputConfigureButtonsActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuSettingsInputConfigureController4" ) )
        {  // open the menu to map controller buttons
            MenuSettingsInputConfigureButtonsActivity.controllerNum = 4;
            Intent intent = new Intent( mInstance, MenuSettingsInputConfigureButtonsActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
        else if( menuOption.info.equals( "menuSettingsInputConfigureCoreFunctions" ) )
        {  // open the menu to map controller buttons
            MenuSettingsInputConfigureButtonsActivity.controllerNum = -1;
            Intent intent = new Intent( mInstance, MenuSettingsInputConfigureButtonsActivity.class );
            intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
            startActivity( intent );
        }
    }
}
