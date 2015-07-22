#include <qimage.h>

#include "../nehewidget.h"

class NeHeChapter7 : public NeHeWidget
{
private:
	GLfloat rotx, roty;
	GLfloat z;
	GLfloat drotx, droty;
	
	GLfloat LightAmbient[4];
	GLfloat LightDiffuse[4];
	GLfloat LightPosition[4];

	bool light;	
	GLuint  filter;                                                                 // Which Filter To Use
	GLuint  texture[3];
	
	void loadGLTextures()
	{
		QImage t;
		QImage b;
		
		if ( !b.load( "../images/crate.bmp" ) )
		{
			b = QImage( 16, 16, 32 );
			b.fill( Qt::green.rgb() );
		}
		
		t = QGLWidget::convertToGLFormat( b );
		glGenTextures( 3, &texture[0] );
		
		glBindTexture( GL_TEXTURE_2D, texture[0] );
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_NEAREST);
		glTexImage2D( GL_TEXTURE_2D, 0, 3, t.width(), t.height(), 0, GL_RGBA, GL_UNSIGNED_BYTE, t.bits() );

		glBindTexture(GL_TEXTURE_2D, texture[1]);
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
		glTexImage2D( GL_TEXTURE_2D, 0, 3, t.width(), t.height(), 0, GL_RGBA, GL_UNSIGNED_BYTE, t.bits() );

		glBindTexture(GL_TEXTURE_2D, texture[2]);
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR_MIPMAP_NEAREST);
		gluBuild2DMipmaps(GL_TEXTURE_2D, 3, t.width(), t.height(), GL_RGBA, GL_UNSIGNED_BYTE, t.bits());
	}
	
public:
	NeHeChapter7( QWidget *parent=0, char *name=0 ) : NeHeWidget( 50, parent, name )
	{
		LightAmbient[0]=  0.5f;
		LightAmbient[1]= 0.5f;
		LightAmbient[2]=  0.5f;
		LightAmbient[3]=	 1.0f;

		LightDiffuse[0]= 1.0f;
		LightDiffuse[1]= 1.0f;
		LightDiffuse[2]= 1.0f;
		LightDiffuse[3]= 1.0f;

		LightPosition[0]= 0.0f;
		LightPosition[1]=0.0f;
		LightPosition[2]=2.0f;
		LightPosition[3]=1.0f;

		rotx = roty = 0.0f;
		z = -5.0f;
		drotx = droty = 0.0f;
		filter = 0;
		light = true;
	}
	
protected:
	void initializeGL()
	{
		loadGLTextures();
		
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);

		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glClearDepth(1.0f);

		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);

		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		
		glLightfv(GL_LIGHT1, GL_AMBIENT, LightAmbient);
		glLightfv(GL_LIGHT1, GL_DIFFUSE, LightDiffuse); 
		glLightfv(GL_LIGHT1, GL_POSITION,LightPosition);
		glEnable(GL_LIGHT1);
	}
	
	void resizeGL( int width, int height )
	{
  	height = height?height:1;

		glViewport( 0, 0, (GLint)width, (GLint)height );

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(45.0f,(GLfloat)width/(GLfloat)height,0.1f,100.0f);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
	}
	
	void paintGL()
	{
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();

		glTranslatef(0.0f,0.0f,z);

		glRotatef(rotx,1.0f,0.0f,0.0f);
		glRotatef(roty,0.0f,1.0f,0.0f);

		glBindTexture(GL_TEXTURE_2D, texture[filter]);  

		glBegin(GL_QUADS);
		// Front Face
		glNormal3f( 0.0f, 0.0f, 1.0f);
		glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f);
		glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f, -1.0f,  1.0f);
		glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f,  1.0f,  1.0f);
		glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f,  1.0f,  1.0f);
		// Back Face
		glNormal3f( 0.0f, 0.0f,-1.0f);
		glTexCoord2f(1.0f, 0.0f); glVertex3f(-1.0f, -1.0f, -1.0f);
		glTexCoord2f(1.0f, 1.0f); glVertex3f(-1.0f,  1.0f, -1.0f);
		glTexCoord2f(0.0f, 1.0f); glVertex3f( 1.0f,  1.0f, -1.0f);
		glTexCoord2f(0.0f, 0.0f); glVertex3f( 1.0f, -1.0f, -1.0f);
		// Top Face
		glNormal3f( 0.0f, 1.0f, 0.0f);
		glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f,  1.0f, -1.0f);
		glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f,  1.0f,  1.0f);
		glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f,  1.0f,  1.0f);
		glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f,  1.0f, -1.0f);
		// Bottom Face
		glNormal3f( 0.0f,-1.0f, 0.0f);
		glTexCoord2f(1.0f, 1.0f); glVertex3f(-1.0f, -1.0f, -1.0f);
		glTexCoord2f(0.0f, 1.0f); glVertex3f( 1.0f, -1.0f, -1.0f);
		glTexCoord2f(0.0f, 0.0f); glVertex3f( 1.0f, -1.0f,  1.0f);
		glTexCoord2f(1.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f);
		// Right face
		glNormal3f( 1.0f, 0.0f, 0.0f);
		glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f, -1.0f, -1.0f);
		glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f,  1.0f, -1.0f);
		glTexCoord2f(0.0f, 1.0f); glVertex3f( 1.0f,  1.0f,  1.0f);
		glTexCoord2f(0.0f, 0.0f); glVertex3f( 1.0f, -1.0f,  1.0f);
		// Left Face
		glNormal3f(-1.0f, 0.0f, 0.0f);
		glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f, -1.0f, -1.0f);
		glTexCoord2f(1.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f);
		glTexCoord2f(1.0f, 1.0f); glVertex3f(-1.0f,  1.0f,  1.0f);
		glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f,  1.0f, -1.0f);
		glEnd(); 
	}
	
	void timeOut()
	{
		rotx+=drotx;
    roty+=droty;
		
		updateGL();
	}
	
	void keyPressEvent( QKeyEvent *e )
	{
		switch( e->key() )
		{
		case Qt::Key_L:
			light = !light;
			
			if( light )
				glEnable( GL_LIGHTING );
			else		
				glDisable( GL_LIGHTING );
		
			break;
			
		case Qt::Key_F:
			filter++;
			if( filter > 2 )
				filter = 0;
		
			break;
			
		case Qt::Key_Left:
			droty -= 0.01f;

			break;
			
		case Qt::Key_Right:
			droty += 0.01f;

			break;
			
		case Qt::Key_Up:
			drotx -= 0.01f;

			break;
			
		case Qt::Key_Down:
			drotx += 0.01f;

			break;
			
		case Qt::Key_PageDown:
			z -= 0.02f;
		
			break;
					
		case Qt::Key_PageUp:
			z += 0.02f;
			
		default:
			NeHeWidget::keyPressEvent( e );
		}
	}
};
