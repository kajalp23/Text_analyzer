package text.analyzer

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
//import io.micronaut.http.annotation.Get


@Controller("/text")
class Exer3Controller {
    var content: String = ""
    var response: String = ""

    @Post(value = "/read/{cont}", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun save(@Body cont: String): HttpResponse<String> {
        content = cont.trim()
        var word_count = 0
        var character_count_with_spaces = content.length
        var character_count_without_spaces = 0
        var line_count = 0

        var words: MutableSet<String> = mutableSetOf()
        var prevSpaceIndex = -1
        var i = 0

        while (i < content.length) {
            var ch = content.get(i)

            if (ch != ' ' && ch != '.' && ch != ',') {
                character_count_without_spaces++
            } else if (ch == '.' || ch == ',' || ch == ' ') {
                if (ch == '.') line_count++
                word_count++
                words.add(content.substring(prevSpaceIndex + 1, i).lowercase())
                if (ch != ' ') i++
                prevSpaceIndex = i
            }
            i++
        }


        var mp: Map<String, Int> = mapOf(
            "word_count" to word_count,
            "character_count_with_spaces" to character_count_with_spaces,
            "character_count_without_spaces" to character_count_without_spaces,
            "line_count" to line_count,
            "unique_words" to words.size
        )
        var res =  mp.toString()
        
        return HttpResponse.ok(res)
    }
}