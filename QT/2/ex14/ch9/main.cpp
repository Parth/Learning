#include <qapplication.h>

#include "ch9.h"

int main( int argc, char **argv )
{
	QApplication a( argc, argv );
	
	NeHeWidget *w = new NeHeChapter9();
	a.setMainWidget( w );
	w->show();
	
	return a.exec();
}
