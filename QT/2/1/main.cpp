int main(int argc, char **argv) {
	QApplication a(argc, argv);
	
	NeHeWidget *w = new NeHeChapterXX();
	a.setMainWidget(w);
	w->show();

	return a.exec();

}
