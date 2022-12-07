import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise7 {
    
    public void Execute(){
        List<String> fileLines;
        try {
            fileLines = Files.readAllLines(ConfigLoader.GetConfigKey("input7"));
            Directory rootDirectory = new Directory("/");
            Directory currentDirectory = rootDirectory;
            fileLines.remove(0);
            for(String line : fileLines){
                if(line.charAt(0) == '$'){
                    if(line.substring(2, 4).equals("cd")){
                        if(line.substring(5).equals("..")){
                            currentDirectory = currentDirectory.GetParentDirectory();
                        } else {
                            String dir = line.substring(5);
                            currentDirectory = (Directory)currentDirectory.Files.stream().filter(x -> x.Name.equals(dir)).findAny().orElse(null);
                        }
                    } else {
                        
                    }
                } else if (Character.isDigit(line.charAt(0))){
                    long size = Long.parseLong(line.substring(0, line.indexOf(' ')));
                    String fileName = line.substring(line.indexOf(' ') + 1);
                    File file = new File(fileName, size);
                    currentDirectory.AddFile(file);
                } else {
                    Directory directory = new Directory(line.substring(4));
                    directory.SetParentDirectory(currentDirectory);
                    currentDirectory.AddFile(directory);
                }
            }

            long result = rootDirectory.GetCalculateSizeSum(100000);  
            List<Long> dirs = rootDirectory.GetDirectoriesSizes().stream().filter(x -> x > 30000000 - (70000000 - rootDirectory.GetSize())).collect(Collectors.toList());
            
            Collections.sort(dirs);

            System.out.println("Amount of data under limit: " + result);
            System.out.println("Deleted partition size: " + dirs.get(0));
        } catch (IOException e) { }
    }

    public class File{
        private String Name;
        private Directory ParentDirectory;
        private long Size;

        public File(String name, long size){
            this.Name = name;
            this.Size = size;
        }

        public Directory GetParentDirectory(){
            return this.ParentDirectory;
        }

        public void SetParentDirectory(Directory directory){
            this.ParentDirectory = directory;
        }

        public long GetSize(){
            return this.Size;
        }
    }

    public class Directory extends File {
        private List<File> Files = new ArrayList<>();

        public Directory(String name){
            super(name, 0);
        }

        public void AddFile(File file){
            this.Files.add(file);
        }

        public long GetCalculateSizeSum(long limit){
            long sum = 0;
            for(File file : Files){
                if(file.Size == 0){
                    Directory dir = (Directory)file;
                    sum += dir.GetCalculateSizeSum(limit);
                    super.Size += dir.GetSize();
                } else {
                    super.Size += file.GetSize();
                }
            }
            sum += this.GetSize() > limit ? 0 : this.GetSize();
            return sum;
        }

        public List<Long> GetDirectoriesSizes(){
            List<Long> dirs = new ArrayList<Long>(){};
            for(File file : this.Files){
                Directory directory = TryCast(file);
                
                if(directory != null && !directory.Files.isEmpty()){
                    dirs.addAll(directory.GetDirectoriesSizes());
                }
            }
            dirs.add(this.GetSize());
            return dirs;
        }

        private Directory TryCast(File file){
            try{
                return (Directory)file;
            } catch (ClassCastException ex){
                return null;
            }
        }
    }
}
