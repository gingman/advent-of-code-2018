import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args : Array<String>) {
    val resource = Int::class.java.getResource("day4.input.txt")
    val input = File(resource.toURI()).readLines()
    println("Part 1: " + Day4.part1(input))
    println("Part 2: " + Day4.part2(input))
}

data class Schedule(val time: LocalDateTime, val action: String)

object Day4 {
    fun part1(inputs: List<String>): Int {
        val guardSleepOccurrence = findSleptMinutesByWorker(inputs)
        val mostSleptGuard = guardSleepOccurrence.entries.sortedBy { it.value.values.sum() }.last().key
        val mostSleptMinute = guardSleepOccurrence[mostSleptGuard]!!.entries.sortedBy { it.value }.last().key

        return mostSleptGuard * mostSleptMinute
    }

    fun part2(inputs: List<String>): Int {
        val guardSleepOccurrence = findSleptMinutesByWorker(inputs)
        val guardWithMostTimeAtSameMinute = guardSleepOccurrence.entries.sortedBy { it.value.values.max() }.last().key
        val mostSleptMinute = guardSleepOccurrence[guardWithMostTimeAtSameMinute]!!.entries.sortedBy { it.value }.last().key

        return guardWithMostTimeAtSameMinute * mostSleptMinute
    }

    private fun findSleptMinutesByWorker(inputs: List<String>): HashMap<Int, HashMap<Int, Int>> {
        val guardSleepOccurrence = hashMapOf<Int, HashMap<Int, Int>>()
        val schedules = inputs.map { parseSchedule(it) }.sortedBy { it.time }
        var currentGuard = 0
        var startMinute = 0

        schedules.forEach { schedule ->
            when {
                schedule.action.contains("Guard") -> {
                    val guardIdRegex = """(\d+)""".toRegex()
                    val matchValue = guardIdRegex.find(schedule.action)
                    val currentGuardId = matchValue!!.value.toInt()

                    if (guardSleepOccurrence[currentGuardId] == null) {
                        guardSleepOccurrence[currentGuardId] = hashMapOf()
                    }

                    currentGuard = currentGuardId
                }
                schedule.action.contains("falls asleep") -> startMinute = schedule.time.minute
                schedule.action.contains("wakes up") -> {
                    val endMinute = schedule.time.minute
                    for (minute in startMinute until endMinute) {
                        if (guardSleepOccurrence[currentGuard]!![minute] == null) {
                            guardSleepOccurrence[currentGuard]!![minute] = 0
                        }
                        guardSleepOccurrence[currentGuard]!![minute] =
                                guardSleepOccurrence[currentGuard]!![minute]!!.plus(1)
                    }
                }
            }
        }
        return guardSleepOccurrence
    }

    private fun parseSchedule(input: String): Schedule {
        val regexp =  """\[(.*?)]\s(.*)""".toRegex()
        val (date, action) = regexp.find(input)!!.destructured
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return Schedule(LocalDateTime.parse(date, formatter), action)
    }
}
