[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/J_c8sizy)
# MassiveMotion
CS 245 Project 02

# Massive Motion Simulation
This project implements a 2D particle simulation to test and compare the performance of four custom-built Java List implementations. Data structures implemented include: ArrayList, LinkedList, DoubleLinkedList, and DummyHeadLinkedList.
1. ArrayList: Implemented using a resizing Object[] array. Insertion and deletion require element shifting. Includes a grow() helper to double capacity when necessary.
2. Singly Linked List: A standard linked list structure using only a head pointer and single next references in each node. Requires traversal for all indexed operations and uses if/else logic to handle updates to the head pointer.
3. Doubly Linked List: An enhanced linked list with head and tail pointers. Nodes contain both next and prev references. Allows for addition and removal at both the head and the tail.
4. Dummy Head LinkedList: A singly linked list that uses a permanent, data-less dummy head node at the start. This sentinel node eliminates the need for special case logic when adding to or removing from the beginning of the list, simplifying the overall code structure.

# How to Run
1. Compile all files
2. Run with configuration file (MassiveMotion.txt)
3. To switch the data structure, edit the MassiveMotion.txt file and change the list property. (arraylist, single, double, and dummyhead)

# Running Implementation
ArrayList Video via Google Drive(https://drive.google.com/file/d/1WNdjRZX4jF1Ixjq0-drzDk9FOvcugD1R/view?usp=sharing)