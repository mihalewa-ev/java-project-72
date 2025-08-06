clean:
	make -C app clean

build:
	make -C app build

install:
	make -C app install

run:
	make -C app run

test:
	make -C app test

report:
	make -C app report

build-run: 
  make -C app build run

installDist:
	make -C app installDist

.PHONY: build
