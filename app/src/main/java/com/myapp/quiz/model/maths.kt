package com.myapp.quiz.model

object Maths {
    fun questions(): List<Questions> = listOf(
        // Arithmetic & Numbers
        Questions(
            1,
            "What is the value of π (pi) approximately?",
            0,
            "3.12",
            "3.14",
            "3.16",
            "3.18",
            2,
            "Mathematics"
        ),
        Questions(
            2,
            "What is the square root of 144?",
            0,
            "10",
            "11",
            "12",
            "14",
            3,
            "Mathematics"
        ),
        Questions(3, "What is 25% of 200?", 0, "25", "50", "75", "100", 2, "Mathematics"),
        Questions(
            4,
            "Which number is a prime number?",
            0,
            "15",
            "21",
            "31",
            "33",
            3,
            "Mathematics"
        ),
        Questions(5, "What is 8 squared?", 0, "16", "64", "32", "24", 2, "Mathematics"),

        // Algebra
        Questions(
            6,
            "What is the value of x in: 2x + 3 = 7?",
            0,
            "1",
            "2",
            "3",
            "4",
            2,
            "Mathematics"
        ),
        Questions(
            7,
            "Simplify: (a + b)²",
            0,
            "a² + b²",
            "a² + 2ab + b²",
            "a² − 2ab + b²",
            "None of the above",
            2,
            "Mathematics"
        ),
        Questions(8, "What is the cube root of 27?", 0, "2", "3", "6", "9", 2, "Mathematics"),
        Questions(9, "Solve for x: x² = 49", 0, "±7", "7", "-7", "0", 1, "Mathematics"),
        Questions(
            10,
            "What is the solution to: 5x = 0",
            0,
            "0",
            "1",
            "5",
            "Undefined",
            1,
            "Mathematics"
        ),

        // Geometry
        Questions(
            11,
            "How many sides does a hexagon have?",
            0,
            "5",
            "6",
            "7",
            "8",
            2,
            "Mathematics"
        ),
        Questions(
            12,
            "What is the sum of interior angles of a triangle?",
            0,
            "90°",
            "180°",
            "270°",
            "360°",
            2,
            "Mathematics"
        ),
        Questions(
            13,
            "A right angle is equal to how many degrees?",
            0,
            "45°",
            "90°",
            "120°",
            "180°",
            2,
            "Mathematics"
        ),
        Questions(
            14,
            "Which shape has all sides equal and all angles 90°?",
            0,
            "Rectangle",
            "Square",
            "Parallelogram",
            "Trapezium",
            2,
            "Mathematics"
        ),
        Questions(
            15,
            "What is the perimeter of a square of side 4 cm?",
            0,
            "12 cm",
            "16 cm",
            "8 cm",
            "20 cm",
            2,
            "Mathematics"
        ),

        // Trigonometry & Mensuration
        Questions(
            16,
            "In right-angled triangle, sinθ = ?",
            0,
            "Opp/Hyp",
            "Adj/Opp",
            "Hyp/Opp",
            "Adj/Hyp",
            1,
            "Mathematics"
        ),
        Questions(17, "Area of a circle = ?", 0, "πr²", "2πr", "πd", "πr", 1, "Mathematics"),
        Questions(
            18,
            "What is the volume of a cube with side 3 cm?",
            0,
            "9 cm³",
            "27 cm³",
            "18 cm³",
            "81 cm³",
            2,
            "Mathematics"
        ),
        Questions(
            19,
            "What is the area of a rectangle with length 5 and breadth 3?",
            0,
            "15",
            "8",
            "10",
            "12",
            1,
            "Mathematics"
        ),
        Questions(20, "Cos(0°) = ?", 0, "1", "0", "Undefined", "-1", 1, "Mathematics"),

        // Logic & Applied Math
        Questions(
            21,
            "What comes next in the pattern: 2, 4, 8, 16, ...?",
            0,
            "24",
            "30",
            "32",
            "36",
            3,
            "Mathematics"
        ),
        Questions(
            22,
            "If A = 1, B = 2, ..., what is the value of 'CAB'?",
            0,
            "6",
            "7",
            "9",
            "8",
            3,
            "Mathematics"
        ),
        Questions(23, "What is 100 divided by 5?", 0, "20", "25", "10", "15", 1, "Mathematics"),
        Questions(
            24,
            "If 3 pencils cost ₹15, what is the cost of 1 pencil?",
            0,
            "₹3",
            "₹4",
            "₹5",
            "₹6",
            3,
            "Mathematics"
        ),
        Questions(
            25,
            "What is the next prime number after 7?",
            0,
            "9",
            "10",
            "11",
            "13",
            3,
            "Mathematics"
        )
    )
}