# Movie Recommender System 🎬

![Java](https://img.shields.io/badge/Language-Java-orange)
![Version](https://img.shields.io/badge/Iteration-2-blue)
![Architecture](https://img.shields.io/badge/Pattern-OOP-green)

This project is a movie recommendation application developed for the **1r DAM 2025-2026** course. In this second iteration, the system has evolved from a basic sequential script into a professional, scalable application using Object-Oriented Programming (OOP).

---

## 👥 Team Constitution and Roles (Group 5)
In this second iteration, our team adopted a structured **SCRUM** methodology to ensure all technical requirements were met with high quality:

* **Mario Marín (Scrum Master & Lead Architect):** Responsible for high-level system design, including the UML Class Diagram. Managed the Database Connection layer and coordinated the final presentation.
* **Prabin Paudel (Product Owner & Logic Designer):** Defined functional requirements for the user experience. Collaborated on the core logic for user-specific lists and movie suggestions.
* **Nizam El Arrassi (Lead Developer & Documenter):** Focused on the technical implementation of the Recommendation Engine and Favorites functionality. Responsible for technical documentation.
* **Valeria Márquez (UI Designer & Refactoring Lead):** Spearheaded the OOP Refactoring process, converting procedural code into a clean, class-based architecture and designing the catalog's visual structure.

---

## 📋 Index
1. [Iteration 2 Planning](#2-iteration-2-planning)
2. [Relationship with Learning Outcomes (RA)](#3-relationship-with-learning-outcomes-ra)
3. [Most Important Design Decisions](#4-most-important-design-decisions)
4. [Detailed Technical Analysis: The Recommendation Engine](#5-detailed-technical-analysis-the-recommendation-engine)
5. [What Can We Improve as a Team?](#6-what-can-we-improve-as-a-team)
6. [Future Project Extensions](#7-future-project-extensions)

---

## 2. Iteration 2 Planning
The primary objective was the architectural transformation of the system. Our planning focused on three main pillars:
* **Security:** Graceful error handling in the authentication system.
* **Scalability:** Implementing a database-ready structure for users.
* **Personalization:** Prioritizing modularity so new features can be plugged in without breaking existing code.

We also placed heavy emphasis on the **GitHub workflow**, using branches to manage concurrent development effectively.

## 3. Relationship with Learning Outcomes (RA)
This project serves as a practical application of the *Resultats d'Aprenentatge* defined in the DAM curriculum:
* **RA1 (Control Structures):** Complex logic using nested loops and conditional statements for filtering and authentication.
* **RA3 (Data Structures):** Using `HashMap` for user management and `ArrayList` for the movie catalog.
* **RA4 & RA7 (OOP & Advanced POO):** Fully class-based project utilizing Encapsulation, constructors, and specialized methods.
* **RA6 (Teamwork & Version Control):** Professional collaboration through GitHub, managing pull requests and resolving conflicts.

## 4. Most Important Design Decisions
* **Strict Encapsulation:** All attributes in `User` and `Movie` classes are private, protecting data integrity. This prevents unauthorized modifications to ratings or credentials.
* **Modular Service Architecture:** The recommendation logic is placed in a specialized engine rather than the `Main` class. This "separation of concerns" allows for independent algorithm updates.
* **Optimized Registry:** Using a `HashMap` for the user registry makes lookups faster and more efficient than searching through standard lists.

## 5. Detailed Technical Analysis: The Recommendation Engine
The engine operates as a filtering system, moving from a "logic-first" hand-drawn sketch to a technical implementation.

### Algorithmic Implementation and Data Flow
1.  **Retrieval:** The system fetches the `preferredGenres` list from the `User` object.
2.  **Comparison:** It executes a for-each loop across the global `ArrayList<Movie>`. If a movie’s genre matches the user's preferences, it is added to a temporary results list.
3.  **Priority Layer:** The algorithm utilizes a sorting mechanism to display the best-rated movies first (descending order).

### Fallback Strategy
If the engine finds no matches (e.g., for a new user), it defaults to displaying **"Trending Movies"** based on the highest global ratings, preventing "empty state" problems and maintaining engagement.

## 6. What Can We Improve as a Team?
* **Earlier Integration:** We identified a need for more frequent "commit and merge" approaches to avoid technical debt and pressure at the end of the sprint.
* **Unit Testing:** We plan to increase the use of automated testing to catch small bugs in the logic before they reach the final build.

## 7. Future Project Extensions
* **Data Persistence:** Connecting the system to external JSON or SQL database files.
* **Graphical User Interface (GUI):** Transitioning from CLI to **JavaFX** to display movie posters and create an immersive experience.
* **Collaborative Filtering:** Implementing advanced algorithms based on preferences of users with similar tastes.

---
> **Course:** 1r DAM 2025-2026 | **Group:** 5