using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Reflection;
using System.Reflection.Metadata.Ecma335;
using System.Text;
using System.Threading.Tasks;

namespace Advent_of_Code_2022
{
    public static class Exercise1
    {
        public static void Execute()
        {
            FileStream fs = File.Open(ConfigurationManager.AppSettings["InputPath1"], FileMode.Open);
            List<List<int>> calories = new List<List<int>>();

            List<int> elf = new List<int>();

            using (StreamReader reader = new StreamReader(fs))
            {
                while (!reader.EndOfStream)
                {
                    string line = reader.ReadLine();
                    if (string.IsNullOrEmpty(line))
                    {
                        calories.Add(new List<int>(elf));
                        elf.Clear();
                        continue;
                    }
                    else
                    {
                        elf.Add(int.Parse(line));
                    }

                }
            }

            List<int> mostCalories = calories.OrderByDescending(x => x.Sum()).Take(3).Select(x => x.Sum()).ToList();

            Console.WriteLine("Most calories: {0}\nSum of three largest: {1}", mostCalories[0], mostCalories.Sum());
        }
    }
}
