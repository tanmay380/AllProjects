using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace WpfApp1
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
           
            InitializeComponent();
        }

        private void hello(object sender, EventArgs e)
        {
            if (this.WindowState == WindowState.Minimized)
                ShowInTaskbar = false;
            else
                ShowInTaskbar = true;
        }

        private void viiblechange(object sender, DependencyPropertyChangedEventArgs e)
        {
            ShowInTaskbar = false;

            if (this.WindowState == WindowState.Minimized)
            {
                ShowInTaskbar = true;
            }
        }
    }
}
