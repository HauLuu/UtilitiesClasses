
import okhttp3.ResponseBody
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

object FileHelper {

    private const val BUFFER_SIZE = 4096

    /**
     * [destinationDirectory] the folder in which the [zipFile]
     * will be hosted
     * */
    fun saveZipFile(zipFile: ResponseBody, destinationDirectory: File): File? {
        try {
            val buffer = ByteArray(BUFFER_SIZE)
            val destinationFile = File(destinationDirectory, "${BuildConfig.FLAVOR}_icons")
            var downloadedByte = 0
            zipFile.byteStream().use { fis ->
                FileOutputStream(destinationFile).use { fos ->
                    var read: Int
                    while (fis.read(buffer).also { read = it } != -1) {
                        fos.write(buffer, 0, read)
                        downloadedByte += read
                    }
                }
            }
            return destinationFile
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    /**
     * [destinationDirectory] the folder in which
     * the content of [zipFile] will be extracted
     * */
    fun unzip(zipFile: File, destinationDirectory: File) {
        ZipInputStream(BufferedInputStream(FileInputStream(zipFile))).use { zis ->
            lateinit var ze: ZipEntry
            var count = 0
            val buffer = ByteArray(BUFFER_SIZE)
            while (zis.nextEntry?.also { ze = it } != null) {
                val file = File(destinationDirectory, ze.name)
                val dir = if (ze.isDirectory) file else file.parentFile

                when {
                    dir.isDirectory.not() && dir.mkdirs()
                            .not() -> throw FileNotFoundException("Unable to make sure ${dir.absolutePath} exits")
                    ze.isDirectory -> continue
                }

                FileOutputStream(file).use { fos ->
                    while (zis.read(buffer).also { count = it } != -1) {
                        fos.write(buffer, 0, count)
                    }
                }
            }
        }
    }
}