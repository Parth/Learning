#include <qimage.h>
#include <qvaluelist.h>
#include <qfile.h>

#include "../nehewidget.h"
class NeHeChapter11 : public NeHeWidget
{
private:
	float points[45][45][3];
	int wiggle_count;

	GLfloat	xrot;
	GLfloat	yrot;
	GLfloat	zrot;

	GLuint	texture[1];

	void loadGLTextures()
	{
		QImage t;
		QImage b;
		
		if ( !b.load( "../images/tim.bmp" ) )
		{
			b = QImage( 16, 16, 32 );
			b.fill( Qt::green.rgb() );
		}
		
		t = QGLWidget::convertToGLFormat( b );
		glGenTextures( 1, &texture[0] );
		
		glBindTexture( GL_TEXTURE_2D, texture[0] );
		glTexImage2D( GL_TEXTURE_2D, 0, 3, t.width(), t.height(), 0, GL_RGBA, GL_UNSIGNED_BYTE, t.bits() );
    glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
	}
	
public:
	NeHeChapter11( QWidget *parent=0, char *name=0 ) : NeHeWidget( 50, parent, name )
	{
		wiggle_count = 0;
		
		xrot = yrot = zrot = 0;
	}
	
protected:
	void initializeGL()
	{
		loadGLTextures();

		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);
		glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		glClearDepth(1.0f);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		glPolygonMode( GL_BACK, GL_FILL );
		glPolygonMode( GL_FRONT, GL_LINE );

		for(int x=0; x<45; x++)
		{
			for(int y=0; y<45; y++)
			{
				points[x][y][0]=float((x/5.0f)-4.5f);
				points[x][y][1]=float((y/5.0f)-4.5f);
				points[x][y][2]=float(sin((((x/5.0f)*40.0f)/360.0f)*3.141592654*2.0f));
			}
		}
	}
	
	void resizeGL( int width, int height )
	{
		height = height?height:1;

		glViewport(0,0,(GLint)width,(GLint)height);

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		gluPerspective(45.0f,(GLfloat)width/(GLfloat)height,0.1f,100.0f);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();			
	}
	
	void paintGL()
	{ 
		int x, y;
		float float_x, float_y, float_xb, float_yb;

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();

		glTranslatef(0.0f,0.0f,-12.0f);

		glRotatef(xrot,1.0f,0.0f,0.0f);
		glRotatef(yrot,0.0f,1.0f,0.0f);  
		glRotatef(zrot,0.0f,0.0f,1.0f);

		glBindTexture(GL_TEXTURE_2D, texture[0]);

		glBegin(GL_QUADS);
		for( x = 0; x < 44; x++ )
		{
			for( y = 0; y < 44; y++ )
			{
				float_x = float(x)/44.0f;
				float_y = float(y)/44.0f;
				float_xb = float(x+1)/44.0f;
				float_yb = float(y+1)/44.0f;

				glTexCoord2f( float_x, float_y);
				glVertex3f( points[x][y][0], points[x][y][1], points[x][y][2] );

				glTexCoord2f( float_x, float_yb );
				glVertex3f( points[x][y+1][0], points[x][y+1][1], points[x][y+1][2] );

				glTexCoord2f( float_xb, float_yb );
				glVertex3f( points[x+1][y+1][0], points[x+1][y+1][1], points[x+1][y+1][2] );

				glTexCoord2f( float_xb, float_y );
				glVertex3f( points[x+1][y][0], points[x+1][y][1], points[x+1][y][2] );
			}
		}
		glEnd();
	}
	
	void timeOut()
	{
		GLfloat hold;
		int x, y;

		if( wiggle_count == 2 )
		{
			for( y = 0; y < 45; y++ )
			{
				hold=points[0][y][2];
				for( x = 0; x < 44; x++)
				{
					points[x][y][2] = points[x+1][y][2];
				}
				points[44][y][2]=hold;
			}
			wiggle_count = 0;
		}

		wiggle_count++;

		xrot+=0.3f;
		yrot+=0.2f;
		zrot+=0.4f;
		
		updateGL();
	}
};
