import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer
import okio.use
import platform.posix.exit

val inString = """
                <artifactId>maven-release-plugin</artifactId>
                <version>2.5.3</version>
            </plugin>
"""

val outString = """
                <artifactId>maven-release-plugin</artifactId>
                <version>3.0.0</version>
            </plugin>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.2</version>
            </plugin>
"""

fun main(args: Array<String>) {
    args.firstOrNull()?.let { filepath ->
        val path = filepath.toPath()
        if (FileSystem.SYSTEM.exists(path)) {
            println("Reading $path")
            val orig = FileSystem.SYSTEM.source(path).use { source ->
                source.buffer().use { bufferedSource ->
                    bufferedSource.readByteArray().decodeToString()
                }
            }

            val modified = orig.replace(inString, outString)

            FileSystem.SYSTEM.write(path) {
                writeUtf8(modified)
            }
            println("Updated $path")
        }
        else {
            println("$path doesn't exist")
        }
    } ?: run {
        println("Specify the file")
        exit(1)
    }
}