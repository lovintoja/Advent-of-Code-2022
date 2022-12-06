using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Advent_of_Code_2022
{
    public static class Exercise2
    {
        public static void Execute()
        {
            Dictionary<char, int> pointsPerMove = new Dictionary<char, int>()
            {
                { 'A', 1 },
                { 'B', 2 },
                { 'C', 3 }
            };

            Dictionary<char, char> winningMoves = new Dictionary<char, char>()
            {
                { 'A', 'B' },
                { 'B', 'C' },
                { 'C', 'A' }
            };

            FileStream fs = File.Open(ConfigurationManager.AppSettings["InputPath2"], FileMode.Open);

            int points = 0;
            int pointsPartTwo = 0;

            using (StreamReader reader = new StreamReader(fs))
            {
                while (!reader.EndOfStream)
                {
                    string line = reader.ReadLine();
                    char[] moves = line.Split(' ').Select(x => x[0]).ToArray();
                    moves[1] = CastToElfCode(moves[1]);
                    if (moves[0] == moves[1])
                    {
                        points += 3;
                    }
                    else if (moves[1] == winningMoves[moves[0]])
                    {
                        points += 6;
                    }
                    points += pointsPerMove[moves[1]];

                    switch (moves[1])
                    {
                        case 'A':
                            pointsPartTwo += pointsPerMove[winningMoves.First(x => x.Key != moves[0] && x.Value != moves[0]).Value];
                            break;
                        case 'B':
                            pointsPartTwo += (3 + pointsPerMove[moves[0]]);
                            break;
                        case 'C':
                            pointsPartTwo += (6 + pointsPerMove[winningMoves[moves[0]]]);
                            break;
                    }

                }
            }

            Console.WriteLine("Points: {0}\nPoints part two: {1}", points, pointsPartTwo);
        }

        private static char CastToElfCode(char x)
        {
            switch (x)
            {
                case 'X':
                    return 'A';
                case 'Y':
                    return 'B';
                case 'Z':
                    return 'C';
                default:
                    return new char();
            }
        }
    }
}
