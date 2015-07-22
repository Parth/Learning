#include <qapplication.h>

#include "ch4.h"

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	
	NeHeWidget *w = new NeHeChapter4();
	a.setMainWidget( w );
	w->show();
	
	return a.exec();
}
