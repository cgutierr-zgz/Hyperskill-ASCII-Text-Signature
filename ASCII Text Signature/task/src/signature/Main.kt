package signature

import java.io.File
import java.util.*

fun main() {

    val scanner = Scanner(System.`in`);
    print("Enter name and surname: ");
    val str = scanner.nextLine();
    print("Enter person's status: ");
    val descr = scanner.nextLine();
    PrimitiveArt('*', str.trim(), descr.trim().toLowerCase());
}

class PrimitiveArt {
    constructor(delimeter:Char, str:String, descr:String)
    {
        InitRomanMap()
        val main_array = CreateMainText(str);
        val descr_array = CreateDescription(descr);
        val ready_msg = CompileMsg(main_array, descr_array);

    }

    fun CompileMsg(strArray:Array<String>, descrArray:Array<String>):Array<String>
    {
        var lenght:Int = 0;// длина сообщения, а не "письма" в рамке
        val out_array = Array<String>(WordHeight + 3 + 2, {""});
        var border_line = "";
        if(strArray[0].length >= descrArray[0].length) {
            lenght = strArray[0].length;
            for(i in strArray.indices) {
                out_array[i+1] = "88  " + strArray[i] + "  88";
            }
            var tmp_lenght:Int = lenght - descrArray[0].length;
            if(tmp_lenght % 2 > 0) {
                for(i in descrArray.indices) {
                    out_array[WordHeight + 1 + i] = "88  " + "".padEnd(tmp_lenght/2, ' ') + descrArray[i] + "".padEnd(tmp_lenght/2+1, ' ') + "  88";
                }
            }
            else {
                for(i in descrArray.indices) {
                    out_array[WordHeight + 1 + i] = "88  " + "".padEnd(tmp_lenght/2-1, ' ') + descrArray[i] + "".padEnd(tmp_lenght/2+1, ' ') + "  88";
                }
            }
            border_line = "".padEnd(lenght+8, '8');
        }
        else {
            lenght = descrArray[0].length;
            var tmp_lenght:Int = lenght - strArray[0].length;
            for(i in strArray.indices) {
                out_array[i+1] = "88  " + "".padEnd(if(tmp_lenght % 2 == 0) tmp_lenght/2 else tmp_lenght/2 + 1, ' ') + strArray[i] + "".padEnd(tmp_lenght/2+1, ' ') + "  88";
            }

            for(i in descrArray.indices) {
                out_array[WordHeight + 1 + i] = "88  " + descrArray[i] + "   88";
            }
            border_line = "".padEnd(lenght+8+1, '8');
        }

        out_array[0] = border_line;
        out_array[out_array.lastIndex] = border_line;
        for(i in out_array.indices) {
            println(out_array[i]);
        }
        return out_array;
    }

    fun InitRomanMap()
    {
        var temp_buf = "";
        val scan = Scanner(File("/Users/cgutierr/Downloads/roman.txt"));
        RomanMap = mutableMapOf<Char, Array<String>>();
        temp_buf = scan.nextLine();
        WordHeight = temp_buf.split(" ")[0].toInt();
        val count = temp_buf.split(" ")[1].toInt();
        for(i in 1..count) {
            val tmp_array:Array<String> = Array<String>(WordHeight, {""});
            temp_buf = scan.nextLine();
            val tmp_char = temp_buf.split(" ")[0][0];
            val word_width = temp_buf.split(" ")[1].toInt();
            for(index in tmp_array.indices) {
                tmp_array[index] = scan.nextLine();
                if(tmp_array[index].isEmpty()){
                    tmp_array[index] = "".padEnd(word_width, ' ');
                }
            }
            RomanMap.put(tmp_char, tmp_array);
        }
    }

    fun CreateMainText(str:String):Array<String>
    {
        val str_array = Array<String>(WordHeight, {""});
        for(i in str.indices) {
            if(str[i] != ' ') {
                val tmp_array:Array<String> = RomanMap[str[i]]!!;
                for(index in str_array.indices){
                    str_array[index] += tmp_array[index];
                }
            }
            else{
                for(index in str_array.indices){
                    str_array[index] += "          ";
                }
            }

        }
        return str_array;
    }

    fun CreateDescription(str:String):Array<String>
    {
        val out: Array<String> = Array<String>(3, {""});

        for(i in str.indices){
            when(str[i]) {
                'a' -> {
                    out[0] += "____";
                    out[1] += "|__|";
                    out[2] += "|  |";
                }
                'b' -> {
                    out[0] += "___ ";
                    out[1] += "|__]";
                    out[2] += "|__]";
                }
                'c' -> {
                    out[0] += "____";
                    out[1] += "|   ";
                    out[2] += "|___";
                }
                'd' -> {
                    out[0] += "___ ";
                    out[1] += "|  \\";
                    out[2] += "|__/";
                }
                'e' -> {
                    out[0] += "____";
                    out[1] += "|___";
                    out[2] += "|___";
                }
                'f' -> {
                    out[0] += "____";
                    out[1] += "|___";
                    out[2] += "|   ";
                }
                'g' -> {
                    out[0] += "____";
                    out[1] += "| __";
                    out[2] += "|__]";
                }
                'h' -> {
                    out[0] += "_  _";
                    out[1] += "|__|";
                    out[2] += "|  |";
                }
                'i' -> {
                    out[0] += "_";
                    out[1] += "|";
                    out[2] += "|";
                }
                'j' -> {
                    out[0] += " _";
                    out[1] += " |";
                    out[2] += "_|";
                }
                'k' -> {
                    out[0] += "_  _";
                    out[1] += "|_/ ";
                    out[2] += "| \\_";
                }
                'l' -> {
                    out[0] += "_   ";
                    out[1] += "|   ";
                    out[2] += "|___";
                }
                'm' -> {
                    out[0] += "_  _";
                    out[1] += "|\\/|";
                    out[2] += "|  |";
                }
                'n' -> {
                    out[0] += "_  _";
                    out[1] += "|\\ |";
                    out[2] += "| \\|";
                }
                'o' -> {
                    out[0] += "____";
                    out[1] += "|  |";
                    out[2] += "|__|";
                }
                'p' -> {
                    out[0] += "___ ";
                    out[1] += "|__]";
                    out[2] += "|   ";
                }
                'q' -> {
                    out[0] += "____";
                    out[1] += "|  |";
                    out[2] += "|_\\|";
                }
                'r' -> {
                    out[0] += "____";
                    out[1] += "|__/";
                    out[2] += "|  \\";
                }
                's' -> {
                    out[0] += "____";
                    out[1] += "[__ ";
                    out[2] += "___]";
                }
                't' -> {
                    out[0] += "___";
                    out[1] += " | ";
                    out[2] += " | ";
                }
                'u' -> {
                    out[0] += "_  _";
                    out[1] += "|  |";
                    out[2] += "|__|";
                }
                'v' -> {
                    out[0] += "_  _";
                    out[1] += "|  |";
                    out[2] += " \\/ ";
                }
                'w' -> {
                    out[0] += "_ _ _";
                    out[1] += "| | |";
                    out[2] += "|_|_|";
                }
                'x' -> {
                    out[0] += "_  _";
                    out[1] += " \\/ ";
                    out[2] += "_/\\_";
                }
                'y' -> {
                    out[0] += "_   _";
                    out[1] += " \\_/ ";
                    out[2] += "  |  ";
                }
                'z' -> {
                    out[0] += "___ ";
                    out[1] += "  / ";
                    out[2] += " /__";
                }
                ' ' -> {
                    out[0] += "    ";
                    out[1] += "    ";
                    out[2] += "    ";
                }
                else -> {}
            }
            if(i < str.lastIndex) {
                out[0] += " ";
                out[1] += " ";
                out[2] += " ";
            }
        }
        return out;
    }

    lateinit var RomanMap:MutableMap<Char, Array<String>>;
    var WordHeight:Int = 0;
}