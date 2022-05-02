JVM = java
JAVAC = javac

DEBUG = -g
CHECK = -Xlint:all -Xdiags:verbose

BINDIR = bin
SRCDIR = src

default:
	@echo type "make compil" and then "make run"


compil:
	if ! test -d $(BINDIR); then mkdir $(BINDIR); fi
	cd $(SRCDIR) ; $(JAVAC) -d ../$(BINDIR)  $(DEBUG) $(CHECK) raytracer/*.java


run:
	cd $(BINDIR) ; $(JVM) raytracer/App $(filename)

clean:
	if test -d $(BINDIR); then rm -r $(BINDIR); fi

