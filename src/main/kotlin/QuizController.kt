import java.io.File
import kotlin.reflect.typeOf

class QuizController {
    val questions: MutableList<Question> = mutableListOf()

    init {
        val fileName: String = javaClass.getResource("/questions.txt").file
        File(fileName).forEachLine { line ->
            val splitLine = line.split(' ')
            val q = splitLine[0]
            val correct = splitLine[1]
            val answers = splitLine.subList(2, splitLine.size).toMutableList()
            questions.add(Question(q, answers, correct))
        }
    }

    private fun randomizeQuestions() {
        questions.shuffle()
    }

    fun doQuiz(numOfQuestions: Int) {
        this.randomizeQuestions()
        var numOfCorrect = 0
        val questionsToBeShown = questions.subList(0, numOfQuestions);
        questionsToBeShown.forEach { q ->
            println("Question: ${q.text}");
            q.answers.shuffle()
            q.answers.zip('a'..'d').forEach { ans ->

                println("${ans.second}. ${ans.first}")
            }
            var o: Char = 'x'
            while (!CharRange('a', 'd').contains(o)) {
                o = readLine().toString()[0]
            }

            if (q.answers.zip('a'..'d').find { ans -> ans.second == o }?.first == q.correctAnswer) numOfCorrect++
        }

        println("Correct answers: $numOfCorrect / Total Questions: $numOfQuestions")

    }
}