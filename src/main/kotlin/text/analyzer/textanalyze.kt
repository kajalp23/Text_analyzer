package text.analyzer

import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.core.io.IOUtils
import io.micronaut.core.util.CollectionUtils
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.json.tree.JsonObject



@Controller("/text")
class StringController {


    @Post(value = "/read", consumes = [MediaType.APPLICATION_JSON])
    fun save(@Body body: UserRes): Resp {

        var cont = body.content.trim()

        var wordSet = mutableSetOf<String>()
        var cnt=0
        var j = 0
        while(j<cont.length){

            if(cont.get(j)!=' ' && cont.get(j)!='.' && cont.get(j)!=','){
                cnt++
                j++
            }
            else{
                wordSet.add(cont.substring(j-cnt,j).lowercase())
                while(j<cont.length && (cont.get(j)==' ' || cont.get(j)=='.' || cont.get(j)==','))
                    j++;
                cnt=0;
            }
        }

        print(wordSet)

        var word_count = 0
        var character_count_with_spaces = cont.length
        var character_count_without_spaces:Int = 0
        var line_count = 0
        var unique_words = wordSet.size

        for(i in 0..cont.length-1){
            if(cont.get(i)!=' ' && cont.get(i)!='.' && cont.get(i)!=',')
                character_count_without_spaces++

            if(cont.get(i)==' ' || cont.get(i)=='.' || cont.get(i)==','){
                if(i>0 && cont.get(i-1)!='.' && cont.get(i-1)!=' ' && cont.get(i)!=',')
                    word_count++
            }

            if(cont.get(i)=='.')
                line_count++
        }

        return Resp(word_count,character_count_with_spaces,character_count_without_spaces,line_count,unique_words)

    }


}