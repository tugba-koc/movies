import { Outlet } from 'react-router';
import Header from './header/Header';

const Layout = () => {
  return (
    <main>
      <div>
        <Header />
        <Outlet />
      </div>
    </main>
  );
};

export default Layout;
