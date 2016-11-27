# data-structures
From time spent as a tutor and teaching assistant, I've developed a few simple data structures from scratch to help students learn or to use as worked examples.

## Queue Golf Activity:

The QueueGolfADT class is the blueprints for a queue data structure, including helpful data structure methods like toArray. This ADT was created primarily for the purposes of playing code golf with my CS 2114 taught the summer of 2016. 

Students should implement the interface in their own version of a queue (my ExampleQueue.java code is my version, which students will not have access to). The QueueTest.java code should be able to use the student's queue an pass all tests for the student's queue to count as functionally complete. Although I can't know the underlying structure of the student's queue, as they are allowed to use linked or array implementations at their own discretion, I do know many of the common pitfalls students make after three semesters of grading student projects. Inline comments explain some of the reasons for testing specific sequences of calls. For example, some students fumble the linked structure updates in dequeues, and sometimes their clear method doesn't reset the size. Students will have this test class in order to run against their queue as they develop for the queue gold activity.

ExampleQueue is my implementation of the queue golf activity to ensure that it can be done, and I achieved it in 111 lines using singly-linked nodes and both a front and back reference. All attempts by students are required to use my same formatting conventions. +++
