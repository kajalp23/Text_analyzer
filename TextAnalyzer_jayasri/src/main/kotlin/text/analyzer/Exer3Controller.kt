package text.analyzer

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.json.tree.JsonObject


@Controller("/text")

class Exer3Controller {
    var content: String = ""

    @Post(value = "/read", consumes = [MediaType.APPLICATION_JSON], produces = [MediaType.APPLICATION_JSON])
    fun save(@Body cont: JsonObject): JsonNode {
        //Input string from request body
        content = cont.get("content").stringValue.trim()

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

        //response json object
        var json: String = "{\"word_count\":${word_count},\"character_count_with_spaces\": ${character_count_with_spaces}, \"character_count_without_spaces\": ${character_count_without_spaces}, \"line_count\": ${line_count}, \"unique_words\": ${words.size} }";
        var mapper: ObjectMapper = ObjectMapper();
        var node: JsonNode = mapper.readTree(json)

        return node
    }

}