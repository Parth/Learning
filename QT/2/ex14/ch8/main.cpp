#include <qapplication.h>

#include "ch8.h"

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	
	NeHeWidget *w = new NeHeChapter8();
	a.setMainWidget( w );
	w->show();
	
	return a.exec();
}
