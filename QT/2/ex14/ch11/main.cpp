#include <qapplication.h>

#include "ch11.h"

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	
	NeHeWidget *w = new NeHeChapter11();
	a.setMainWidget( w );
	w->show();
	
	return a.exec();
}
