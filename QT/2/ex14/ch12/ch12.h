#include <qimage.h>
#include <qvaluelist.h>
#include <qfile.h>

#include "../nehewidget.h"

static GLfloat boxcol[5][3]=
{
	{1.0f,0.0f,0.0f},{1.0f,0.5f,0.0f},{1.0f,1.0f,0.0f},{0.0f,1.0f,0.0f},{0.0f,1.0f,1.0f}
};

static GLfloat topcol[5][3]=
{
	{.5f,0.0f,0.0f},{0.5f,0.25f,0.0f},{0.5f,0.5f,0.0f},{0.0f,0.5f,0.0f},{0.0f,0.5f,0.5f}
};

class NeHeChapter12 : public NeHeWidget
{
private:
	GLuint	texture[1];
	GLuint	box;				
	GLuint	top;				

	GLfloat	xrot;				
	GLfloat	yrot;				

	void buildLists()
	{
	box=glGenLists(2);
	glNewList(box,GL_COMPILE);
		glBegin(GL_QUADS);
			// Bottom Face
			glNormal3f( 0.0f,-1.0f, 0.0f);
			glTexCoord2f(1.0f, 1.0f); glVertex3f(-1.0f, -1.0f, -1.0f);
			glTexCoord2f(0.0f, 1.0f); glVertex3f( 1.0f, -1.0f, -1.0f);
			glTexCoord2f(0.0f, 0.0f); glVertex3f( 1.0f, -1.0f,  1.0f);
			glTexCoord2f(1.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f);
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
	glEndList();
	top=box+1;
	glNewList(top,GL_COMPILE);
		glBegin(GL_QUADS);
			// Top Face
			glNormal3f( 0.0f, 1.0f, 0.0f);
			glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f,  1.0f, -1.0f);
			glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f,  1.0f,  1.0f);
			glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f,  1.0f,  1.0f);
			glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f,  1.0f, -1.0f);
		glEnd();
	glEndList();
	}

	void loadGLTextures()
	{
		QImage t;
		QImage b;
		
		if ( !b.load( "../images/cube.bmp" ) )
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
	NeHeChapter12( QWidget *parent=0, char *name=0 ) : NeHeWidget( 0, parent, name )
	{
		xrot = yrot = 0;
	}
	
protected:
	void initializeGL()
	{
		loadGLTextures();
		buildLists();

		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);
		glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		glClearDepth(1.0f);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glEnable(GL_LIGHT0);
		glEnable(GL_LIGHTING);
		glEnable(GL_COLOR_MATERIAL);	
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
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
		GLuint	xloop;			
		GLuint	yloop;			

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		glBindTexture(GL_TEXTURE_2D, texture[0]);
		for (yloop=1;yloop<6;yloop++)
		{
			for (xloop=0;xloop<yloop;xloop++)
			{
				glLoadIdentity();							// Reset The View
				glTranslatef(1.4f+(float(xloop)*2.8f)-(float(yloop)*1.4f),((6.0f-float(yloop))*2.4f)-7.0f,-20.0f);
				glRotatef(45.0f-(2.0f*yloop)+xrot,1.0f,0.0f,0.0f);
				glRotatef(45.0f+yrot,0.0f,1.0f,0.0f);
				glColor3fv(boxcol[yloop-1]);
				glCallList(box);
				glColor3fv(topcol[yloop-1]);
				glCallList(top);
			}
		}
	}

	void keyPressEvent( QKeyEvent *e )
	{
		switch( e->key() )
		{
		case Qt::Key_Up:
			xrot -= 0.2f;
			updateGL();

			break;
		case Qt::Key_Down:
			xrot += 0.2f;
			updateGL();

			break;
		case Qt::Key_Left:
			yrot -= 0.2f;
			updateGL();
			
			break;
		case Qt::Key_Right:
			yrot += 0.2f;
			updateGL();

			break;
		default:
			NeHeWidget::keyPressEvent( e );
		}
	}
};
